// #option 1
public class AwesomeButtonActivity extends AppCompatActivity {
    private Button awesomeButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        awesomeButton = new Button(this);

        awesomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                awesomeButtonClicked();
            }
        });
    }
    private void awesomeButtonClicked() {
        awesomeButton.setText("AWESOME!");
    }
}
// #option 2
public class AwesomeButtonActivity extends AppCompatActivity {
    private Button awesomeButton; 
    private View.OnClickListener awesomeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            awesomeButtonClicked();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        awesomeButton = new Button(this);
        awesomeButton.setOnClickListener(awesomeOnClickListener);
    }
    private void awesomeButtonClicked() {
        awesomeButton.setText("AWESOME!");
    }
}
// #option 3
public class AwesomeButtonActivity extends AppCompatActivity {
    private Button awesomeButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        awesomeButton = new Button(this);

        awesomeButton.setOnClickListener(new AwesomeButtonClick());
    }
    private void awesomeButtonClicked() {
        awesomeButton.setText("AWESOME!");
    }
    class AwesomeButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            awesomeButtonClicked();
        }
    }
}
// #option 4
public class AwesomeButtonActivity extends AppCompatActivity implements View.OnClickListener {
    private Button awesomeButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        awesomeButton = new Button(this);

        awesomeButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        awesomeButtonClicked();
    }
    private void awesomeButtonClicked() {
        awesomeButton.setText("AWESOME!");
    }
}
// ref: https://hackernoon.com/4-ways-to-implement-onclicklistener-on-android-9b956cbd2928
