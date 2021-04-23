package com.example.zarema_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Spinner spinner;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        textView = findViewById(R.id.textView);
        textView.setTextIsSelectable(true);
        imageView = findViewById(R.id.imageView);
        JSONUpdateTask task = new JSONUpdateTask();
        task.execute(spinner.getSelectedItem().toString());
    }

    // Кнопка "Обновить"
    public void onClick(View view) {
        new JSONUpdateTask().execute(spinner.getSelectedItem().toString());
    }

    private class JSONUpdateTask extends AsyncTask<String, Void, Followers> {



        @Override
        protected Followers doInBackground(String... params) {
            return FollowersBuilder.buildFollowers(params[0]);
        }
        // ----------------------------------------------------------------------------


        @Override
        protected void onPostExecute(final Followers followers) {
            super.onPostExecute(followers);
            imageView.post(new Runnable() { //  Используем синхронизацию с UI
                @Override
                public void run() {

                    if (followers.getIcon() != null) {
                        imageView.setImageBitmap(followers.getIcon());
                    } else {
                        imageView.setImageResource(R.mipmap.ic_launcher); // Установка своей иконки при ошибке
                    }
                    imageView.invalidate(); // Принудительная прорисовка картинки на всякий случай
                }
            });
            //вывод подписок
            textView.post(new Runnable() { //  с использованием синхронизации UI
                @Override
                public void run() {
                    textView.setText("");
                    if (followers.getName() != null) {
                        textView.append("ID: " + followers.getId() + "\n");
                        textView.append("Login: " + followers.getName() + "\n");
                        textView.append("Ссылка: " + followers.getUserURL()+ "\n");
                        textView.append("Ссылка на репозитории: " + followers.getRepository());
                    } else {
                        textView.append("Нет данных" + "\n");
                        textView.append("Проверьте подключение с интернетом");
                    }
                }
            });

        }
        // ------------------------------------------------------------------------------------

    }
}