package ru.project.gbrecyclertest;

import android.content.Context;
import android.view.View;

import android.widget.Toast;

public class RouteObjectType implements RowType {

    private String timeForObject;
    private String routeObjectName;
    private Context context; // чтобы повесить листенер и передавать контекст!!

    public RouteObjectType(String timeForObject, String routeObjectName, Context context) {
        this.timeForObject = timeForObject;
        this.routeObjectName = routeObjectName;
        this.context = context;
    }

    public String getTimeForObject() {
        return timeForObject;
    }

    public String getRouteObjectName() {
        return routeObjectName;
    }

    public View.OnClickListener getOnShowClickListener(final int pos) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Показать на карте "+pos, Toast.LENGTH_SHORT).show();
            }
        };
    }

    public View.OnClickListener getOnDeleteClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Удалить!", Toast.LENGTH_SHORT).show();
            }
        };
    }

}
