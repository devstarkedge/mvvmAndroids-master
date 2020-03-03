package com.app.fitv1.Base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Constructor;
import java.util.List;

public abstract class SuperAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>
{

    List<T> modelList;

    public SuperAdapter(List<T> modelClass)
    {
        modelList = modelClass;
    }

    @Override
    public int getItemCount()
    {
        return modelList.size();
    }


//    @Override
//    public int getItemViewType(int position)
//    {
//        return position;
//    }

    public T getItem(int position)
    {
        return modelList.get(position);
    }


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType)
    {
        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(getLayout(), parent, false);
        try
        {
            Constructor<VH> constructor = getViewHolder().getConstructor(View.class);
            return constructor.newInstance(view);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int position)
    {
        T model = getItem(position);
        populateViewHolderG(viewHolder, model, position);
    }


    public void removeItem(int position)
    {
        if (position < modelList.size())
        {
            modelList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addItem(int position, T data)
    {
        modelList.add(position, data);
        notifyItemInserted(position);
    }

    public void addItems(List<T> data)
    {
        modelList.addAll(data);
        notifyItemRangeInserted(modelList.size() - data.size(), data.size());
    }


    public void notifyDataChangesG(List<T> dataList)
    {
        modelList.clear();
        modelList.addAll(dataList);
        notifyDataSetChanged();
    }


    abstract protected void populateViewHolderG(VH viewHolder, T model, int position);

    abstract protected int getLayout();

    abstract protected Class<VH> getViewHolder();
}


