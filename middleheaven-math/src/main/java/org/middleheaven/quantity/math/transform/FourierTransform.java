package org.middleheaven.quantity.math.transform;

import org.middleheaven.collections.nw.ArraySequence;
import org.middleheaven.collections.nw.EditableSequence;
import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.quantity.math.Complex;

/**
 *  @See http://www.cs.princeton.edu/introcs/97data/FFT.java.html
 */
public class FourierTransform implements Transformation<Sequence<Complex>>{
	
	
//    public Vector<Complex> fowardTransform(Vector<Complex> data) {
//    	Complex[] x = new Complex[data.size()];
//    	Complex[]  fx = fowardTransform(data.toArray(x));
//    	
//    	return Vector.vector(fx);
//	}
//    
//	@Override
//	public Vector<Complex> reverseTransform(Vector<Complex> data) {
//		Complex[] x = new Complex[data.size()];
//		Complex[]  fx = reverseTransform(data.toArray(x));
//    	
//    	return Vector.vector(fx);
//	}
    
	// compute the FFT of x[], assuming its length is multiple of 2
    public Sequence<Complex> fowardTransform(Sequence<Complex> x) {
    	
    	return denseFowardTransform(x);
    }


	private EditableSequence<Complex> denseFowardTransform(Sequence<Complex> x) {

        int N = x.size();

        // base case
        if (N == 1){
        	ArraySequence<Complex> single = ArraySequence.ofLength(1);
        	single.set(0 ,x.get(0)); 
        	return single;
        }

        // radix-2 Cooley-Tukey FFT
        if (N % 2 != 0) { 
        	final int nearest2Power = N + N%2;
        	x = pad(x,Complex.ZERO(),nearest2Power);
        }

        // fft of even terms
        final int halfLength = N/2;
		ArraySequence<Complex> even = ArraySequence.ofLength(halfLength);
        ArraySequence<Complex> odd = ArraySequence.ofLength(halfLength);

        for (int k = 0; k < halfLength; k++) {
            even.set(k , x.get(2*k));
            odd.set(k , x.get(2*k + 1));
        }
        
        Sequence<Complex> q = fowardTransform(even);
        Sequence<Complex> r = fowardTransform(odd);

        ArraySequence<Complex> y = ArraySequence.ofLength(N);
        // combine
        for (int k = 0; k < halfLength; k++) {
            double kth = -2 * k * Math.PI / N;
            
            Complex wk = Complex.rectangular(Math.cos(kth), Math.sin(kth));
            
            y.set(k  , q.get(k).plus(wk.times(r.get(k))));
            y.set(k + halfLength , q.get(k).minus(wk.times(r.get(k))));
        }
        return y;
	}


    // compute the inverse FFT of x[], assuming its length is a multiple of 2
    public Sequence<Complex> reverseTransform(Sequence<Complex> x) {
        final int N = x.size();
   
        EditableSequence<Complex> y = ArraySequence.ofLength(N);
        // take conjugate
        for (int i = 0; i < N; i++) {
            y.set(i , x.get(i).conjugate());
        }

        // compute forward FFT
        y = denseFowardTransform(y);

        // take conjugate again
     // divide by N
        for (int i = 0; i < N; i++) {
            y.set(i , y.get(i).conjugate().times(1.0 / N));
        }

        return y;

    }

    // compute the circular convolution of x and y
    public Sequence<Complex> cconvolve(Sequence<Complex>  x, Sequence<Complex>  y) {

        // pad x and y with 0s so that they have same length
        // and are multiples of 2
        if (x.size() != y.size()) { 
        	int max = Math.max(x.size(), y.size());
        	if ( max % 2 !=0){
        		max += max %2; 
        	}
        	x = pad(x, Complex.ZERO(), max);
        	y = pad(y, Complex.ZERO(), max);
        }

        // compute FFT of each sequence
        Sequence<Complex> a = fowardTransform(x);
        Sequence<Complex> b = fowardTransform(y);

        // point-wise multiply
        
        Sequence<Complex> c = a.zip(b, ( ax,by) -> ax.times(by));

        // compute inverse FFT
        return reverseTransform(c);
    }


    private Sequence<Complex> pad(Sequence<Complex> original, Complex pad, int max) {
    	
    	Sequence<Complex> all = original;

    	for (int i = all.size(); i < max ; i++){
    		all = all.concat(pad);
    	}
    	
		return all;
	}


	// compute the linear convolution of x and y
    public Sequence<Complex> convolve(Sequence<Complex>x, Sequence<Complex> y) {
        final Complex ZERO = Complex.ZERO();

        EditableSequence<Complex> a = ArraySequence.ofLength(2*x.size());
        
        for (int i = 0;        i <   x.size(); i++){
        	a.set(2*i , x.get(i));
        	a.set(2*i+1 , ZERO);
        }

        EditableSequence<Complex> b = ArraySequence.ofLength(2*y.size());

        for (int i = 0;        i <   y.size(); i++) {
        	b.set(2*i , y.get(i));
        	b.set(2*i+1 , ZERO);
        }

        return cconvolve(a, b);
    }







}