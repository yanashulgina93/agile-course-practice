package ru.unn.agile.IntersectionOfSegments.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.IntersectionOfSegments.model.Intersection;
import ru.unn.agile.IntersectionOfSegments.model.Point;
import ru.unn.agile.IntersectionOfSegments.model.Segment;
import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty seg1Point1X = new SimpleStringProperty();
    private final StringProperty seg1Point1Y = new SimpleStringProperty();
    private final StringProperty seg1Point2X = new SimpleStringProperty();
    private final StringProperty seg1Point2Y = new SimpleStringProperty();

    private final StringProperty seg2Point1X = new SimpleStringProperty();
    private final StringProperty seg2Point1Y = new SimpleStringProperty();
    private final StringProperty seg2Point2X = new SimpleStringProperty();
    private final StringProperty seg2Point2Y = new SimpleStringProperty();

    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();

    private final StringProperty status = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        seg1Point1X.set("");
        seg1Point1Y.set("");
        seg1Point2X.set("");
        seg1Point2Y.set("");

        seg2Point1X.set("");
        seg2Point1Y.set("");
        seg2Point2X.set("");
        seg2Point2Y.set("");

        result.set("");

        status.set(Status.WAITING.toString());

        BooleanBinding couldFind = new BooleanBinding() {
            {
                super.bind(seg1Point1X, seg1Point1Y, seg1Point2X, seg1Point2Y,
                        seg2Point1X, seg2Point1Y, seg2Point2X, seg2Point2Y);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldFind.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(seg1Point1X);
            add(seg1Point1Y);
            add(seg1Point2X);
            add(seg1Point2Y);

            add(seg2Point1X);
            add(seg2Point1Y);
            add(seg2Point2X);
            add(seg2Point2Y);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void calculate() {
        if (calculationDisabled.get()) {
            return;
        }

        Segment segment1 = new Segment(getPoint(seg1Point1X, seg1Point1Y),
                getPoint(seg1Point2X, seg1Point2Y));

        Segment segment2 = new Segment(getPoint(seg2Point1X, seg2Point1Y),
                getPoint(seg2Point2X, seg2Point2Y));

        setStringResult(segment1.isIntersectedWith(segment2));

        status.set(Status.SUCCESS.toString());
    }

    private Point getPoint(final StringProperty x, final StringProperty y) {
        return new Point(Double.parseDouble(x.get()), Double.parseDouble(y.get()));
    }

    private void setStringResult(final Intersection intersection) {
        switch (intersection.getTypeOfIntersection()) {
            case NotIntersection:
                result.set("Segments not intersection.");
                break;
            case IntersectionInOnePoint:
                result.set("Segments intersection in one point "
                        + convertPointToString(intersection.getSegment().getStart())
                        + ".");
                break;
            case OnePartOfOther:
            case SegmentsHaveCommonPart:
                result.set("Segments have common part ["
                        + convertPointToString(intersection.getSegment().getStart())
                        + "; " + convertPointToString(intersection.getSegment().getFinish())
                        + "].\nLength = "
                        + Double.toString(intersection.getSegment().getLengthSegment()));
                break;
            default:
                break;
        }
    }

    private String convertPointToString(final Point p) {
        return "(" + Double.toString(p.getX()) + "; " + Double.toString(p.getY()) + ")";
    }
    public StringProperty seg1Point1XProperty() {
        return seg1Point1X;
    }

    public StringProperty seg1Point1YProperty() {
        return seg1Point1Y;
    }

    public StringProperty seg1Point2XProperty() {
        return seg1Point2X;
    }

    public StringProperty seg1Point2YProperty() {
        return seg1Point2Y;
    }

    public StringProperty seg2Point1XProperty() {
        return seg2Point1X;
    }

    public StringProperty seg2Point1YProperty() {
        return seg2Point1Y;
    }

    public StringProperty seg2Point2XProperty() {
        return seg2Point2X;
    }

    public StringProperty seg2Point2YProperty() {
        return seg2Point2Y;
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public final boolean getCalculationDisabled() {
        return calculationDisabled.get();
    }

    public StringProperty resultProperty() {
        return result;
    }

    public final String getResult() {
        return result.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public final String getStatus() {
        return status.get();
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (seg1Point1X.get().isEmpty() || seg1Point1Y.get().isEmpty()
         || seg1Point2X.get().isEmpty() || seg1Point2Y.get().isEmpty()
                || seg2Point1X.get().isEmpty() || seg2Point1Y.get().isEmpty()
                || seg2Point2X.get().isEmpty() || seg2Point2Y.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!seg1Point1X.get().isEmpty()) {
                Double.parseDouble(seg1Point1X.get());
            }
            if (!seg1Point1Y.get().isEmpty()) {
                Double.parseDouble(seg1Point1Y.get());
            }
            if (!seg1Point2X.get().isEmpty()) {
                Double.parseDouble(seg1Point2X.get());
            }
            if (!seg1Point2Y.get().isEmpty()) {
                Double.parseDouble(seg1Point2Y.get());
            }
            if (!seg2Point1X.get().isEmpty()) {
                Double.parseDouble(seg2Point1X.get());
            }
            if (!seg2Point1Y.get().isEmpty()) {
                Double.parseDouble(seg2Point1Y.get());
            }
            if (!seg2Point2X.get().isEmpty()) {
                Double.parseDouble(seg2Point2X.get());
            }
            if (!seg2Point2Y.get().isEmpty()) {
                Double.parseDouble(seg2Point2Y.get());
            }
         } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }

        return inputStatus;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }
}

enum Status {
    WAITING("Please provide input data"),
    READY("Press 'Find of intersection' or Enter"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;
    private Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
