package net.three4j.extras;

import org.mozilla.dom.Image;

public class ImageUtils {

//	Image _canvas;

	public String getDataURL(Image image) {
		return image.src;

//		if ( /^data:/i.test( image.src ) ) {
//
//			return image.src;
//
//		}
//
//		if ( typeof HTMLCanvasElement == 'undefined' ) {
//
//			return image.src;
//
//		}
//
//		Image canvas;
//
//		// image extends HTMLCanvasElement. Notice SUPERclass.isAssignableFrom(SUBclass)
//		if ( HTMLCanvasElement.class.isAssignableFrom(image.getClass())) {
//
//			canvas = image;
//
//		} else {
//
//			if ( _canvas === undefined ) _canvas = document.createElementNS( 'http://www.w3.org/1999/xhtml', 'canvas' );
//
//			_canvas.width = image.width;
//			_canvas.height = image.height;
//
//			const context = _canvas.getContext( '2d' );
//
//			if ( image instanceof ImageData ) {
//
//				context.putImageData( image, 0, 0 );
//
//			} else {
//
//				context.drawImage( image, 0, 0, image.width, image.height );
//
//			}
//
//			canvas = _canvas;
//
//		}
//
//		if ( canvas.width > 2048 || canvas.height > 2048 ) {
//
//			return canvas.toDataURL( 'image/jpeg', 0.6 );
//
//		} else {
//
//			return canvas.toDataURL( 'image/png' );
//
//		}

	}

}