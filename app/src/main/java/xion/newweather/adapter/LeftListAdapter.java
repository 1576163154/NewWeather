package xion.newweather.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import xion.newweather.R;
import xion.newweather.bean.CityWeatherInfo;

/**
 * Created by Administrator on 2017/1/4.
 */

public class LeftListAdapter extends BaseAdapter {
private List<CityWeatherInfo.DataBean.ForecastBean> forecastList;

    public LeftListAdapter(List<CityWeatherInfo.DataBean.ForecastBean> forecastList) {
        this.forecastList = forecastList;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return forecastList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_left_item,null);
        CityWeatherInfo.DataBean.ForecastBean forecastBean = forecastList.get(position);
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            holder.tv_future_type = (TextView) itemView.findViewById(R.id.tv_future_type);
            holder.tv_future_low = (TextView) itemView.findViewById(R.id.tv_future_low);
            holder.tv_future_high = (TextView) itemView.findViewById(R.id.tv_future_high);
            holder.tv_future_date = (TextView) itemView.findViewById(R.id.tv_future_date);
            itemView.setTag(holder);
        }else {
            holder = (ViewHolder) itemView.getTag();
            holder.tv_future_type.setText(forecastBean.getType());
            holder.tv_future_low.setText(forecastBean.getLow());
            holder.tv_future_high.setText(forecastBean.getHigh());
            holder.tv_future_date.setText(forecastBean.getDate());
        }
        return itemView;
    }
    class ViewHolder{
        TextView tv_future_type;
        TextView tv_future_low;
        TextView tv_future_high;
        TextView tv_future_date;
    }
}
