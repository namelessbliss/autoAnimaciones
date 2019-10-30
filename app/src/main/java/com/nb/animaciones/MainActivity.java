package com.nb.animaciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView mGridView;
    private GridAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //establecer grid y adapter
        mGridView = findViewById(R.id.gridView);
        mGridView.setOnItemClickListener(this);
        mAdapter = new GridAdapter();
        mGridView.setAdapter(mAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Item item = (Item) adapterView.getItemAtPosition(position);

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constantes.EXTRA_ID, item.getId());

        // Obtiene referencia a los componentes de vista del gridview
        ImageView ivPhoto = view.findViewById(R.id.ivItem);
        TextView tvName = view.findViewById(R.id.tvName);

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new Pair<View, String>(ivPhoto, Constantes.SHARED_VIEW_PHOTO),
                new Pair<View, String>(tvName, Constantes.SHARED_VIEW_TITLE));

        ActivityCompat.startActivity(this, intent, optionsCompat.toBundle());
    }

    private class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Item.ITEMS.length;
        }

        @Override
        public Item getItem(int position) {
            return Item.ITEMS[position];
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.grid_item, viewGroup, false);
            }

            final Item item = getItem(position);

            // Cargar thumbnail
            ImageView image = view.findViewById(R.id.ivItem);

            Picasso.with(image.getContext())
                    .load(item.getThumbnailUrl())
                    .into(image);

            // Setear el texto
            TextView name = view.findViewById(R.id.tvName);
            name.setText(item.getName());

            return view;
        }
    }
}
