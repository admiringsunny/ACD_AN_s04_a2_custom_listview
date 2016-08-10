package com.acadgild.s4A2CustomListview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomListView extends AppCompatActivity {
    ListView customisedListView;
    ContactsAdapter contactsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);

// initialise ListView
        customisedListView = (ListView) findViewById(R.id.list_item);

// create object to Adapter class
        contactsAdapter = new ContactsAdapter();

// attach adapter to ListView
        customisedListView.setAdapter(contactsAdapter);

// set On Item click Listener
        customisedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactElements contactElements = contactsAdapter.getContactData(position);

                Toast.makeText(CustomListView.this, contactElements.contactName, Toast.LENGTH_SHORT).show();
            }
        });
    }


// set data for list
    public class ContactElements {
        String contactName;
        String contactDescription;
    }
    public List<ContactElements> getChapterNameAndDesc() {

        List<ContactElements> contactElementsList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            ContactElements contactElements = new ContactElements();
            contactElements.contactName = "Name" + i;
            contactElements.contactDescription = "PhoneNumber" + i;

            contactElementsList.add(contactElements);
        }

        return contactElementsList;
    }



// create Adapter class (extends BaseAdapter)
    public class ContactsAdapter extends BaseAdapter {

        List<ContactElements> contactElementsList = getChapterNameAndDesc();

        @Override
        public int getCount() {
            return contactElementsList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) CustomListView.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_item, viewGroup, false);
            }

            TextView contactName = (TextView) view.findViewById(R.id.c_name);
            TextView contactDesc = (TextView) view.findViewById(R.id.c_no);

            ContactElements contactElements = contactElementsList.get(position);

            contactName.setText(contactElements.contactName);
            contactDesc.setText(contactElements.contactDescription);

            return view;
        }

        public ContactElements getContactData(int position){
            return contactElementsList.get(position);
        }
    }
}
