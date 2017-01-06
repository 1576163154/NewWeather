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

public class RightListAdapter extends BaseAdapter {
    private List<CityWeatherInfo> cityWeatherInfoList;

//    public RightListAdapter(List<CityWeatherInfo> cityWeatherInfoList) {
//        this.cityWeatherInfoList = cityWeatherInfoList;
//    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return cityWeatherInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_right_item, null);
        CityWeatherInfo cityWeatherInfo = cityWeatherInfoList.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            holder.tv_future_type = (TextView) itemView.findViewById(R.id.tv_future_type);
            holder.tv_future_low = (TextView) itemView.findViewById(R.id.tv_future_low);
            holder.tv_future_high = (TextView) itemView.findViewById(R.id.tv_future_high);
            holder.tv_future = (TextView) itemView.findViewById(R.id.tv_future);
            itemView.setTag(holder);
        } else {
            holder = (ViewHolder) itemView.getTag();
//            holder.tv_future.setText(cityWeatherInfo.getData().getCity());
//            holder.tv_future_type.setText(cityWeatherInfo.getData().getForecast().get(0).getType());
//            holder.tv_future_low.setText(cityWeatherInfo.getData().getForecast().get(0).getLow());
//            holder.tv_future_high.setText(cityWeatherInfo.getData().getForecast().get(0).getHigh());
            holder.tv_future.setText("北京");
            holder.tv_future_type.setText("晴");
            holder.tv_future_low.setText("18");
            holder.tv_future_high.setText("26");
        }
        return itemView;
    }

    class ViewHolder {
        TextView tv_future;
        TextView tv_future_type;
        TextView tv_future_low;
        TextView tv_future_high;

    }
}
