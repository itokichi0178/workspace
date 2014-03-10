package special_interest_group.ateam.kony.db;

public final class AlarmSettingsConstants {

	/**
	 * プライベートコンストラクタ
	 * インスタンス化を抑止
	 */
	private AlarmSettingsConstants() {}

	/**
	 * 「繰り返し」を示す列挙体
	 */
	public static enum Repeat {
		  None             (0, "なし")
		, EveryDay         (1, "毎日")
		, DayOfTheWeek     (2, "曜日指定")
		, Weekday          (3, "平日")
		, SundayAndHolidays(4, "日祝日")
		;

		 /** 値 */
		private int value;
		 /** 名前 */
		private String name;

		private Repeat(int value, String name) {
			this.value = value;
			this.name  = name;
		}

		/**
		 * 値取得
		 * @return 値
		 */
		public int getValue() {
			return value;
		}

		/**
		 * 名前取得
		 * @return 名前
		 */
		public String getName() {
			return name;
		}
	}

	/**
	 * 値から列挙子取得
	 * @param value 値
	 * @return 列挙子
	 */
	public static Repeat parseRepeat(int value) {
		switch (value) {
			case 0: return Repeat.None;
			case 1: return Repeat.EveryDay;
			case 2: return Repeat.DayOfTheWeek;
			case 3: return Repeat.Weekday;
			case 4: return Repeat.SundayAndHolidays;
			default: throw new IllegalArgumentException("想定外の値が設定されています。value = [" + value + "]");
		}
	}



	/**
	 * 「曜日」を示す列挙体
	 */
	public static enum DaysOfWeek {
		  日("日")
		, 月("月")
		, 火("火")
		, 水("水")
		, 木("木")
		, 金("金")
		, 土("土")
		;

		 /** 値 */
		private String value;

		private DaysOfWeek(String value) {
			this.value = value;
		}

		/**
		 * 値取得
		 * @return 値
		 */
		public String getValue() {
			return value;
		}
	}

	/**
	 * 値から列挙子取得
	 * @param value 値
	 * @return 列挙子
	 */
	public static DaysOfWeek parseDaysOfWeek(String value) {
		     if (value.equals("日")) {return DaysOfWeek.日;}
		else if (value.equals("月")) {return DaysOfWeek.月;}
		else if (value.equals("火")) {return DaysOfWeek.火;}
		else if (value.equals("水")) {return DaysOfWeek.水;}
		else if (value.equals("木")) {return DaysOfWeek.木;}
		else if (value.equals("金")) {return DaysOfWeek.金;}
		else if (value.equals("土")) {return DaysOfWeek.土;}
		else {
			throw new IllegalArgumentException("想定外の値が設定されています。value = [" + value + "]");
		}
	}

}
