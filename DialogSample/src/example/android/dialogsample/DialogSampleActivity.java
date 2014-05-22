package example.android.dialogsample;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class DialogSampleActivity extends Activity {

	TextView label = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog_sample);

		label = (TextView) findViewById(R.id.tv_message);

		Button dialogBtn = (Button) findViewById(R.id.bt_dialog);
		dialogBtn.setTag("dialog");
		dialogBtn.setOnClickListener(new ButtonClickListener());

		Button txtDialogBtn = (Button) findViewById(R.id.bt_textdialog);
		txtDialogBtn.setTag("textDialog");
		txtDialogBtn.setOnClickListener(new ButtonClickListener());

		Button SingleSelecDialogtBtn = (Button) findViewById(R.id.bt_selsctdialog);
		SingleSelecDialogtBtn.setTag("singleSelectDialog");
		SingleSelecDialogtBtn.setOnClickListener(new ButtonClickListener());

		Button DatePickerDialogBtn = (Button) findViewById(R.id.bt_datadialog);
		DatePickerDialogBtn.setTag("datePickerDialogBtn");
		DatePickerDialogBtn.setOnClickListener(new ButtonClickListener());

		Button TimePickerDialogBtn = (Button) findViewById(R.id.bt_timedialog);
		TimePickerDialogBtn.setTag("timePickerDialogBtn");
		TimePickerDialogBtn.setOnClickListener(new ButtonClickListener());

		Button ProgressDialogBtn = (Button) findViewById(R.id.bt_progressdialog);
		ProgressDialogBtn.setTag("progressDialogBtn");
		ProgressDialogBtn.setOnClickListener(new ButtonClickListener());
	}

	class ButtonClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			String tag = (String) v.getTag();

			if (tag.equals("dialog")) {
				showDialog();
			} else if (tag.equals("textDialog")) {
				showTextDialog();
			} else if (tag.equals("singleSelectDialog")) {
				showSingleSelectDialog();
			} else if (tag.equals("datePickerDialogBtn")) {
				showDatePickerDialog();
			} else if (tag.equals("timePickerDialogBtn")) {
				showTimePickerDialog();
			} else if (tag.equals("progressDialogBtn")) {
				showProgressDialog();
			}
		}
	}

	private void showDialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(DialogSampleActivity.this);
		dialog.setTitle("通常ダイアログ");
		dialog.setMessage("選択してください");
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				label.setText("通常ダイアログ:OKが選択されました。");
			}
		});
		dialog.setNegativeButton("NG", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				label.setText("通常ダイアログ:NGが選択されました。");
			}
		});
		dialog.show();
	}

	public void showTextDialog() {
		final EditText editText = new EditText(DialogSampleActivity.this);
		AlertDialog.Builder dialog = new AlertDialog.Builder(DialogSampleActivity.this);
		dialog.setTitle("テキストダイアログ");
		dialog.setMessage("テキストを入力してください。");
		dialog.setView(editText);
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				label.setText("テキストダイアログ:" + editText.getText().toString() + "が入力されました。");
			}
		});
		dialog.show();
	}

	final String[] items = new String[] { "もも", "うめ", "さくら" };
	int which = 0;

	public void showSingleSelectDialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(DialogSampleActivity.this);
		dialog.setTitle("単一選択ダイアログ");
		dialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				which = whichButton;
			}
		});

		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				String selected = items[which];
				label.setText("単一選択ダイアログ:" + selected + "が選択されました。");
			}
		});
		dialog.show();
	}

	public void showDatePickerDialog() {
		Calendar cal = Calendar.getInstance();

		DatePickerDialog dialog = new DatePickerDialog(DialogSampleActivity.this, which,
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						label.setText("日付選択ダイアログ:" + year + "年" + (monthOfYear + 1) + "月"
								+ dayOfMonth + "日");
					}
				}, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
		dialog.show();
	}

	public void showTimePickerDialog() {
		TimePickerDialog dialog = new TimePickerDialog(DialogSampleActivity.this,
				new TimePickerDialog.OnTimeSetListener() {

					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						label.setText("時間選択ダイアログ:" + hourOfDay + "持" + minute + "分");
					}
				}, 0, 0, true);
		dialog.show();
	}

	ProgressDialog dialog;

	public void showProgressDialog() {
		dialog = new ProgressDialog(DialogSampleActivity.this);
		dialog.setTitle("プログレスバーダイアログ");
		dialog.setMessage("しばらくお待ちください。");
		dialog.setIndeterminate(false);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		// dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setMax(100);
		dialog.setCancelable(false);
		dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
			}
		});
		dialog.show();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					for (int i = 0; i < dialog.getMax(); i++) {
						dialog.setProgress(i);
						Thread.sleep(100);
					}
				} catch (Exception e) {

				}
				dialog.dismiss();
			}
		}).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_dialog_sample, menu);
		return true;
	}

}
