rem �Q�l
rem http://dev.classmethod.jp/smartphone/android/android-sqlite/
rem AndroidManifest.xml �� /manifest/application/@android:debuggable �� "true" �ɐݒ肳��Ă���K�v������

set cmd_adb=C:\adt\sdk\platform-tools\adb.exe
set package=special_interest_group.ateam.kony
set db_name=KonyDB

%cmd_adb% -d shell "run-as %package% cat /data/data/%package%/databases/%db_name% > /sdcard/%db_name%.db"
%cmd_adb% pull /sdcard/%db_name%.db
