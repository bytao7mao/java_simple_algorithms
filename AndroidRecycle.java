//for animator
https://github.com/willowtreeapps/spruce-android/blob/master/app/src/main/java/com/willowtreeapps/spurceexampleapp/fragments/RecyclerFragment.java

package com.example.taolen.javarecipes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class RecycleViewCode extends RecyclerView.Adapter<RecycleViewCode.CodeViewHolder> {
    private List<Code> codeList;
    private ItemClickListener mClickListener;
    
    RecycleViewCode(List<Code> codeList){
        this.codeList=codeList;
    }
    
    @Override
    public CodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new CodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CodeViewHolder holder, int position) {
        holder.myTextView.setText(codeList.get(position).getTitle());
        //holder.author.setText(codeList.get(position).getAuthor());
        
    }

    @Override
    public int getItemCount() {
        return codeList.size();
    }


    public class CodeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView myTextView;
        //TextView author;
        CodeViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvCodeName);
            //author=itemView.findViewById(//todo);
            
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
       }

    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


}

////////////////////
package com.example.taolen.javarecipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import io.github.kbiakov.codeview.CodeView;
import io.github.kbiakov.codeview.adapters.Options;
import io.github.kbiakov.codeview.classifier.CodeProcessor;
import io.github.kbiakov.codeview.highlight.ColorTheme;
import io.github.kbiakov.codeview.highlight.Font;

public class MainActivity extends AppCompatActivity implements RecycleViewCode.ItemClickListener {
    ArrayList<Code> codes = new ArrayList<>();
    RecycleViewCode adapter;
    RecyclerView recyclerView;
    
    String desc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up recycle
        recyclerView = (RecyclerView) findViewById(R.id.rvCode);
        adapter=new RecycleViewCode(codes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);     
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        
        initCodeData();  
    }

        // data to populate the RecyclerView with
        private void initCodeData(){
        Code code0 = new Code("Title", "author");
        codes.add(code0);
        Code code1 = new Code("Title1", "author1");
        codes.add(code1);
        Code code2 = new Code("Title2", "author2");
        codes.add(code2);
        adapter.notifyDataSetChanged();
        }
    @Override
    public void onItemClick(View view, int position) {

        Toast.makeText(this,
                "You clicked " +
                        adapter.getItem(position) +
                        " on row number " + position,
                Toast.LENGTH_SHORT).show();

        // auto language recognition
        switch (position) {
            case 0:
                desc = getString(R.string.index0);
                break;
            case 1:
                desc = getString(R.string.index1);
                break;
            default:
                desc = "defaultaaaaaaa";
                break;
                }
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("tao", desc);
                startActivity(intent);
        }
}
///////////////////////////////////
package com.example.taolen.javarecipes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import io.github.kbiakov.codeview.CodeView;
import io.github.kbiakov.codeview.adapters.Format;
import io.github.kbiakov.codeview.adapters.Options;
import io.github.kbiakov.codeview.classifier.CodeProcessor;
import io.github.kbiakov.codeview.highlight.ColorTheme;
import io.github.kbiakov.codeview.highlight.ColorThemeData;
import io.github.kbiakov.codeview.highlight.Font;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // train classifier on app start
        CodeProcessor.init(this);
        CodeView codeView = (CodeView) findViewById(R.id.code_view);

        String desc="";
        Bundle lastIntent = getIntent().getExtras();
        if(lastIntent != null){
            desc = lastIntent.getString("tao");
        }
        codeView.setCode(desc, "java");
//        codeView.setOptions(Options.Default.get(this)
//                .withLanguage("java")
//                .withCode(desc)
//                .withTheme(ColorTheme.MONOKAI)
//                .withFont(Font.Consolas).withShadows());

    }

}
