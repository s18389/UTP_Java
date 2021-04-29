package TESTING;

import java.awt.*;

public final class TaskStatusPresentationUtility {

    public static Color getBackgroundColor(TaskStatus status){
        switch (status){
            case Pending: return Color.yellow;
            case Running: return Color.blue;
            case Accomplished: return Color.green;
            case Failed: return Color.red;
            default: return null;
        }
    }

}
