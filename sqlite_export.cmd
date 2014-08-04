rem 参考
rem http://dev.classmethod.jp/smartphone/android/android-sqlite/
rem AndroidManifest.xml の /manifest/application/@android:debuggable が "true" に設定されている必要がある

set cmd_adb=C:\adt\sdk\platform-tools\adb.exe
set package=special_interest_group.ateam.kony
set db_name=KonyDB

%cmd_adb% -d shell "run-as %package% cat /data/data/%package%/databases/%db_name% > /sdcard/%db_name%.db"
%cmd_adb% pull /sdcard/%db_name%.db
