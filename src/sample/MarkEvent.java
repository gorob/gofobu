package sample;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class MarkEvent extends Event {
    public static final EventType MARK_EVENT_TYPE = new EventType<>(Event.ANY, "IMAGE_MARK");

    public MarkEvent(Object source, EventTarget target) {
        super(source, target, MARK_EVENT_TYPE);
    }
}
