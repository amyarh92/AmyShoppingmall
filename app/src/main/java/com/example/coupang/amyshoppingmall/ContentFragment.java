package com.example.coupang.amyshoppingmall;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amy on 2015-08-17.
 */
public class ContentFragment extends Fragment {

    private ListView mListView = null;
    private ListViewAdapter mAdapter = null;
    private TextView mHeadline;
    private String mCategory = "DEFULT CATEGORY";

    private ContentVolley contentVolley;
    private List<Product> itemlist;

    private FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_content,container,false);

        mListView = (ListView)v.findViewById(R.id.plp_list);
        mListView.setOnItemClickListener(mItemClickListener);


        mHeadline = (TextView)v.findViewById(R.id.headline);
        if (getArguments() != null){
            mCategory = getArguments().getString("category");
        }
        mHeadline.setText(mCategory);


        itemlist = new ArrayList<>();


        contentVolley = new ContentVolley(getActivity()) {
            @Override
            public void requestData() {
                Response.Listener<JSONArray> successListener =  new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        Log.d("TAG", "onResponse");
                        for (int i = 0; i<jsonArray.length(); i++){
                            try{
                                JSONObject obj = jsonArray.getJSONObject(i);
                                Product product = new Product(obj.getLong("productId"),
                                        obj.getString("title"), obj.getLong("originPrice"),
                                        obj.getLong("salePrice"), obj.getString("imageUrl"),
                                        obj.getLong("categoryId"));
                                Log.d("TAG", "product    :    "+ product.getTitle());
                                itemlist.add(product);
                                Log.d("TAG", "itemlist   :    "+ itemlist.size());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter = new ListViewAdapter(getActivity(),itemlist);
                        mListView.setAdapter(mAdapter);
                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                };

                if(mCategory.equals("Top")){
                    contentVolley.getRequestQueue().add(new JsonArrayRequest("http://192.168.212.224:8080/api/categories/1",successListener, errorListener));
//                    contentVolley.getRequestQueue().add(new JsonArrayRequest("http://192.168.212.224:8080/api/categories/1",successListener, errorListener));
                }
                else if(mCategory.equals("Bottom")){
//                    contentVolley.getRequestQueue().add(new JsonArrayRequest("http://192.168.0.7:8080/api/categories/2",successListener, errorListener));
                    contentVolley.getRequestQueue().add(new JsonArrayRequest("http://192.168.212.224:8080/api/categories/2",successListener, errorListener));
                }
                else if(mCategory.equals("One-Piece")){
//                    contentVolley.getRequestQueue().add(new JsonArrayRequest("http://192.168.0.7:8080/api/categories/3",successListener, errorListener));
                    contentVolley.getRequestQueue().add(new JsonArrayRequest("http://192.168.212.224:8080/api/categories/3",successListener, errorListener));
                }
                else if(mCategory.equals("Bag/Shoes")){
//                    contentVolley.getRequestQueue().add(new JsonArrayRequest("http://192.168.0.7:8080/api/categories/4",successListener, errorListener));
                    contentVolley.getRequestQueue().add(new JsonArrayRequest("http://192.168.212.224:8080/api/categories/4",successListener, errorListener));
                }else if(mCategory.equals("Accessory")){
//                    contentVolley.getRequestQueue().add(new JsonArrayRequest("http://192.168.0.7:8080/api/categories/5",successListener, errorListener));
                    contentVolley.getRequestQueue().add(new JsonArrayRequest("http://192.168.212.224:8080/api/categories/5",successListener, errorListener));
                }else {
//                    contentVolley.getRequestQueue().add(new JsonArrayRequest("http://192.168.0.7:8080/api/products",successListener, errorListener));
                    contentVolley.getRequestQueue().add(new JsonArrayRequest("http://192.168.212.224:8080/api/products",successListener, errorListener));
                }

            }
        };

        contentVolley.requestData();

        return v;
    }

    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Product product = (Product) parent.getItemAtPosition(position);
            Long productId = product.getProdcutId();

            Log.d("ItemTAG", "getProdutId  : " + productId.toString());

            PDPFragment fragment_pdp = new PDPFragment();
            setFragment(fragment_pdp, productId);

        }
    };

    //Setting Fragment
    public void setFragment(Fragment fragment, Long productId){
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);

        Bundle bundle = new Bundle();
        bundle.putLong("productId", productId);
        fragment.setArguments(bundle);

        fragmentTransaction.commit();
    }
    private class ListViewAdapter extends BaseAdapter {
        private Context mContext;
        private List<Product> mListData;

        public ListViewAdapter(Context mContext, List<Product> plplist){
            super();
            this.mContext = mContext;
            this.mListData = plplist;
        }

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class ViewHolder{
            public ImageView mItem;
            public TextView mTitle;
            public TextView mPrice;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                convertView = inflater.inflate(R.layout.plplistview, null);

                holder.mItem = (ImageView) convertView.findViewById(R.id.product_img);
                holder.mTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.mPrice = (TextView) convertView.findViewById(R.id.tv_price);

                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            Product mData = mListData.get(position);

            if (mData != null) {
                holder.mItem.setVisibility(View.VISIBLE);
//                holder.mItem.(mData.getImageUrl());
                Glide.with(getActivity()).load(mData.getImageUrl()).into(holder.mItem);
            }else{

                holder.mItem.setVisibility(View.GONE);
            }

            holder.mTitle.setText(mData.getTitle().toString());
            holder.mPrice.setText(mData.getSalePrice().toString());

            return convertView;
        }
    }

    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }

}