package special_interest_group.ateam.kony.db;

/**
 * DBから取得した一行のデータを保持するためのEntityクラス
 *
 */
public class AlarmSettingsEntity {

	/**
	 * rowId
	 */
	private int rowId;

	/**
	 * ON/OFF
	 * <pre>0:OFF 1:ON</pre>
	 */
	private int enable;

	/**
	 * 時刻(時)
	 * <pre>00～23</pre>
	 */
	private int hour;

	/**
	 * 時刻(分)
	 * <pre>00～59</pre>
	 */
	private int minute;

	/**
	 * 繰り返し
	 * <pre>0:なし 1:毎日 2:曜日指定 3:平日 4:日祝日</pre>
	 */
	private int repeat;

	/**
	 * 曜日選択
	 * <pre>チェックした曜日の先頭１文字を連結して格納　例）火，土曜日の場合：火土</pre>
	 */
	private String daysOfWeek;

	/**
	 *アラーム音
	 */
	private String sound;

	/**
	 * スーヌズ設定
	 * <pre>0:なし 5:5分 10:10分 30:30分</pre>
	 */
	private int snooze;

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getRepeat() {
		return repeat;
	}

	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}

	public String getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(String daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public int getSnooze() {
		return snooze;
	}

	public void setSnooze(int snooze) {
		this.snooze = snooze;
	}

}
