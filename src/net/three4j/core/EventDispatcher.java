package net.three4j.core;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * https://github.com/mrdoob/eventdispatcher.js/
 */

public class EventDispatcher {
	public HashMap<String, ArrayList<EventListener>> listeners;

	public EventDispatcher() {
		listeners = new HashMap<>();
	}

	public void addEventListener( String type, EventListener listener ) {

		if ( listeners.get(type) == null ) {

			listeners.put(type, new ArrayList<EventListener>());

		}

		if ( listeners.get(type).indexOf( listener ) == - 1 ) {

			listeners.get(type).add( listener ); // DPP: I've never liked that this wasn't a Set.

		}

	}

	public boolean hasEventListener( String type, EventListener listener ) {

		return listeners.get(type) != null && listeners.get(type).indexOf( listener ) != - 1;

	}

	public void removeEventListener( String type, EventListener listener ) {

		final ArrayList<EventListener> listenerArray = listeners.get(type);

		if ( listenerArray != null ) {

			int index = listenerArray.indexOf( listener );

			if ( index != - 1 ) {

				listenerArray.remove( index );

			}

		}

	}

	public void dispatchEvent( Event event ) {

		final ArrayList<EventListener> listenerArray = listeners.get(event.type);

		if ( listenerArray != null ) {

			event.target = this;

			// Make a copy, in case listeners are removed while iterating.
			@SuppressWarnings("unchecked")
			final ArrayList<EventListener> array = (ArrayList<EventListener>)listenerArray.clone();

			for ( int i = 0, l = array.size(); i < l; i ++ ) {

				array.get(i).call( this, event );

			}

		}

	}

}
