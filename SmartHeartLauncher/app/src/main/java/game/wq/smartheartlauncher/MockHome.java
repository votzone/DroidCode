package game.wq.smartheartlauncher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MockHome extends AppCompatActivity {

    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_home);

        tvResult = findViewById(R.id.tv_result);
        StringBuilder sbuilder = new StringBuilder();
        String action = getIntent().getAction();
        if(action !=null){
            sbuilder.append("action == ").append(action);
        }else {
            sbuilder.append("action == null");
        }
        sbuilder.append("\nisTaskRoot == ").append(isTaskRoot());
        tvResult.setText(sbuilder.toString());
    }
}
