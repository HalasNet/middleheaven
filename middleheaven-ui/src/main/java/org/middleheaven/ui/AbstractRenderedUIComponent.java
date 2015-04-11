/**
 * 
 */
package org.middleheaven.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.middleheaven.global.text.LocalizableText;
import org.middleheaven.global.text.ParsableFormatter;
import org.middleheaven.ui.components.UICommand;
import org.middleheaven.ui.components.UIContainer;
import org.middleheaven.ui.components.UILayout;
import org.middleheaven.ui.components.UIWindow;
import org.middleheaven.ui.components.UIWindowsListener;
import org.middleheaven.ui.data.UIDataContainer;
import org.middleheaven.ui.property.Property;
import org.middleheaven.ui.property.ValueProperty;

/**
 * 
 */
public abstract class AbstractRenderedUIComponent implements UIComponent , UIContainer , UICommand , UIWindow{

	private String id;
	private String familly;
	protected Class<? extends UIComponent> type;
	private UIComponent parent;
	protected List<UIComponent> children = new LinkedList<UIComponent>();
	protected Property<Boolean> visible = ValueProperty.writable("visible", true);
	protected Property<Boolean> enabled = ValueProperty.writable("enabled", true);
	protected Property<Boolean> required = ValueProperty.writable("required", false);
	protected Property<String> name = ValueProperty.writable("name", String.class);
	protected Property<Integer> maxLength = ValueProperty.writable("maxLength", Integer.class);
	protected Property<Integer> minLength = ValueProperty.writable("minLength", Integer.class);
	protected Property<Serializable> value = ValueProperty.writable("value", Serializable.class);
	protected Property<UIReadState> readState = ValueProperty.writable("readState", UIReadState.class);
	protected Property<LocalizableText> text = ValueProperty.writable("text", LocalizableText.class);
	protected Property<ParsableFormatter> formatter = ValueProperty.writable("formatter", ParsableFormatter.class);
	protected UILayout layout;
	protected List<CommandListener> commandListeners = new ArrayList<CommandListener>();
	protected UIDataContainer container;

	protected AbstractRenderedUIComponent (){
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(){
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UISize getDisplayableSize() {
		return UISize.pixels(0, 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UIPosition getPosition() {
		return UIPosition.pixels(0, 0);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDisplayableSize(UISize size) {
		throw new UnsupportedOperationException("Not supported");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getGID() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setGID(String id) {
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRendered() {
		return true;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T extends UIComponent> Class<T> getComponentType() {
		return (Class<T>) this.type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getFamily() {
		return this.familly;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFamily(String familly) {
		this.familly = familly;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UIComponent getUIParent() {
		return this.parent;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUIParent(UIComponent parent) {
	   this.parent = parent;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UIComponent> getChildrenComponents() {
		return Collections.unmodifiableList(children);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getChildrenCount() {
		return children.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addComponent(UIComponent component) {
		children.add(component);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeComponent(UIComponent component) {
		children.remove(component);
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isType(Class<? extends UIComponent> type) {
		return type.isAssignableFrom(this.getComponentType());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUIContainerLayout(UILayout component) {
		this.layout = component;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UILayout getUIContainerLayout() {
		return this.layout;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addComponent(UIComponent component, UILayoutConstraint layoutConstrain) {
		
		if (this.layout != null){
			this.layout.addComponent(component, layoutConstrain);
			this.addComponent(component);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Property<Boolean> getVisibleProperty() {
		return visible;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Property<Boolean> getEnableProperty() {
		return enabled;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Property<LocalizableText> getTextProperty() {
		return text;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Property<String> getNameProperty() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addCommandListener(CommandListener listener) {
		commandListeners.add(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeCommandListener(CommandListener listener) {
		commandListeners.remove(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<CommandListener> getCommandListeners() {
		return commandListeners;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Property<LocalizableText> getTitleProperty() {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addPrespectiveListener(UIPrespectiveListener listener) {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removePrespectiveListener(UIPrespectiveListener listener) {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<UIPrespectiveListener> getPrespectiveListeners() {
		throw new UnsupportedOperationException("Not implememented yet");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addUIWindowListener(UIWindowsListener listener) {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeUIWindowListener(UIWindowsListener listener) {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<UIWindowsListener> getUIWindowListeners() {
		throw new UnsupportedOperationException("Not implememented yet");
	}

}
