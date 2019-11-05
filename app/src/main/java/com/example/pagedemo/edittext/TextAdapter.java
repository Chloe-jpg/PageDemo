package com.example.pagedemo.edittext;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pagedemo.R;

import java.util.List;


public class TextAdapter extends BaseAdapter  {


    int hhh;
    private List<Text> texts;

    private Integer resource;

    private Context context;

    private LayoutInflater inflater;
    public int[] colors = { Color.WHITE, Color.rgb(219, 238, 244) };//RGB颜色

    private AddressNoListener addressNoListener;  //定义

    public interface AddressNoListener{

        void addressNo(int position);  //确定传出的值
        void titleNo(String title);

    }

//    public AddressNoListener getAddressNoListener(){return addressNoListener;}
    public void setAddressNoListener(AddressNoListener addressNoListener)
    {this.addressNoListener = addressNoListener;}

    public TextAdapter(Context context, List<Text> text,int resource){

        this.texts=text;

        this.resource=resource;

        this.context=context;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }



    public int getCount() {

        // TODO Auto-generated method stub

        return texts.size();

    }



    public Object getItem(int arg0) {

        // TODO Auto-generated method stub

        return texts.get(arg0);

    }



    public long getItemId(int arg0) {

        // TODO Auto-generated method stub

        return arg0;

    }




    public View getView(int arg0, View arg1, final ViewGroup arg2) {

        // TODO Auto-generated method stub

        if(arg1==null){

            arg1=inflater.inflate(resource, null);

        }

        final Text text = texts.get(arg0);

        final TextView titleView=(TextView)arg1.findViewById(R.id.title);

        Button currentView=(Button) arg1.findViewById(R.id.current);
        currentView.setOnClickListener(new View.OnClickListener(){
            @Override
             public void onClick(View v){
                addressNoListener.titleNo(text.getCurrent());
                addressNoListener.addressNo(hhh);
            }
         });
        final Spinner contentView=(Spinner)arg1.findViewById(R.id.content);

        contentView.setTag("");
        contentView.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                text.setId(arg2);//每次Spinner中的值改变，Text类中的id就要改变
                try{
                      hhh=arg2;
//                    addressNoListener.addressNo(arg2);
                }catch(NullPointerException e)
                {
                    System.out.println("发生异常的原因为 :"+e.getMessage());
                }
//                addressNoListener.addressNo(arg2);

            }

            public void onNothingSelected(AdapterView<?> arg0) {

                // TODO Auto-generated method stub

            }

        });






        titleView.setText(text.getTitle());
        titleView.setTextSize(13);
        currentView.setText(text.getCurrent());
        currentView.setTextSize(13);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,text.getContent());
        contentView.setAdapter(adapter);
        arg1.setBackgroundColor(colors[arg0 % 2]);// 每隔item之间颜色不同

        return arg1;


    }

}

