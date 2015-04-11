package org.middleheaven.global.location;

import org.middleheaven.domain.criteria.EntityCriteriaBuilder;
import org.middleheaven.domain.store.DomainStore;
import org.middleheaven.quantity.measure.AngularMeasure;

public class LandmarkDataStorageRepository extends LandmarkRepository {


	private DomainStore storage;

	private class CategorizedLandmark {
		private Landmark landmark;
		private LandmarkCategory category;

		private CategorizedLandmark(Landmark landmark, String category) {
			super();
			this.landmark = landmark;
			this.category = new LandmarkCategory(category);
		}

		/**
		 * Obtains {@link Landmark}.
		 * @return the landmark
		 */
		public Landmark getLandmark() {
			return landmark;
		}

		/**
		 * Obtains {@link LandmarkCategory}.
		 * @return the category
		 */
		public LandmarkCategory getCategory() {
			return category;
		}

		
	}
	@Override
	public void addLandmark(Landmark landmark, String category) {
		CategorizedLandmark cl = new CategorizedLandmark(landmark, category);
		storage.store(cl);
	}

	@Override
	public void removeLandmark(Landmark landmark, String category) {
		CategorizedLandmark cl = new CategorizedLandmark(landmark, category);
		storage.store(cl);
	}

	@Override
	public void deleteCategory(String category) {
		storage.remove(new LandmarkCategory(category));
	}

	@Override
	public void deleteLandmark(Landmark landmark) {
		storage.remove(landmark);
	}

	@Override
	public Iterable<String> getCategories() {
		return storage.createQuery(
				EntityCriteriaBuilder.search(LandmarkCategory.class).all()
		).execute().fetchAll().map(it -> it.getName());
	}

	@Override
	public Iterable<Landmark> getLandmarks() {
		return storage.createQuery(EntityCriteriaBuilder.search(Landmark.class).all()).execute().fetchAll();
	}

	@Override
	public Iterable<Landmark> getLandmarks(String category, 
			AngularMeasure minLongitude,AngularMeasure minLatitude, 
			AngularMeasure maxLongitude,AngularMeasure maxLatitude) {

		return storage.createQuery(EntityCriteriaBuilder.search(CategorizedLandmark.class)
				.and(it -> it.getLandmark()).navigateTo()
				.and(it -> it.getCoordinates().getLatitude()).whereComparableValue().bewteen(minLatitude,maxLatitude)
				.and(it -> it.getCoordinates().getLongitude()).whereComparableValue().bewteen(minLongitude, maxLongitude)
				.back()
				.and(it -> it.getCategory()).navigateTo().and(it-> it.getName()).whereText().eq(category).back()
				.all()
		).execute().fetchAll().map( it -> it.getLandmark());
	}

	@Override
	public Iterable<Landmark> getLandmarks(String category, String name) {
		return storage.createQuery(EntityCriteriaBuilder.search(CategorizedLandmark.class)
				.and(it -> it.getCategory()).navigateTo().and(it -> it.getName()).whereValue().eq(category).back()
				.and(it -> it.getLandmark()).navigateTo().and(it->it.getName()).whereValue().eq(name).back()
				.all()
		).execute().fetchAll().map( it -> it.getLandmark());
	}




}
