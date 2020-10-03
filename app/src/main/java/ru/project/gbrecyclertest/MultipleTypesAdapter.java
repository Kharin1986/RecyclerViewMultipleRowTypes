package ru.project.gbrecyclertest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MultipleTypesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements ItemTouchHelperAdapter {
    private final OnStartDragListener mDragStartListener;
    private List<RowType> dataSet;

    public MultipleTypesAdapter(List<RowType> dataSet, OnStartDragListener dragStartListener) {
        this.mDragStartListener = dragStartListener;
        this.dataSet = dataSet;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataSet.get(position) instanceof RouteObjectType) {
            return RowType.ROUTE_OBJECT_TYPE;
        } else if (dataSet.get(position) instanceof DayNumberType) {
            return RowType.DAY_NUMBER_TYPE;
        } else {
            return -1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RowType.ROUTE_OBJECT_TYPE) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.route_object_item, parent, false);
            return new RouteObjectHolder(view);
        } else if (viewType == RowType.DAY_NUMBER_TYPE) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.day_number_item, parent, false);
            return new DayNumberHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RouteObjectHolder) {
            ((RouteObjectHolder) holder).bShowOnMap
                    .setOnClickListener(((RouteObjectType) dataSet.get(position)).getOnShowClickListener(position));// для примера
            ((RouteObjectHolder) holder).bDeleteFromRoute
                    .setOnClickListener(((RouteObjectType) dataSet.get(position)).getOnDeleteClickListener());
            ((RouteObjectHolder) holder).tvTimeForObject
                    .setText(((RouteObjectType) dataSet.get(position)).getTimeForObject());
            ((RouteObjectHolder) holder).tvRouteObjectName
                    .setText(((RouteObjectType) dataSet.get(position)).getRouteObjectName());
        } else if (holder instanceof DayNumberHolder) {
            ((DayNumberHolder) holder).tvDayNumber
                    .setText(((DayNumberType) dataSet.get(position)).getText());
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(dataSet, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        dataSet.remove(position);
        notifyItemRemoved(position);
    }

    public static class RouteObjectHolder extends RecyclerView.ViewHolder {

        public Button bShowOnMap;
        public Button bDeleteFromRoute;
        public TextView tvTimeForObject;
        public TextView tvRouteObjectName;

        public RouteObjectHolder(View itemView) {
            super(itemView);
            bShowOnMap = (Button) itemView.findViewById(R.id.btn_show_on_map);
            bDeleteFromRoute = (Button) itemView.findViewById(R.id.btn_delete_from_route);
            tvTimeForObject = (TextView) itemView.findViewById(R.id.tv_time_for_object);
            tvRouteObjectName = (TextView) itemView.findViewById(R.id.tv_route_object_name);
        }

    }

    public static class DayNumberHolder extends RecyclerView.ViewHolder {
        public TextView tvDayNumber;
        public DayNumberHolder(View itemView) {
            super(itemView);
            tvDayNumber = (TextView) itemView.findViewById(R.id.tv_day_number);
        }
    }
}