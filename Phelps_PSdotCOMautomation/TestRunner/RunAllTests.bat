echo Choose target Environment&echo 1. devpsstaging.phelpsagency.com&echo 2. psstaging.phelpsagency.com&echo 3. test-web.ps.com&echo 4. stage-web.ps.com &echo 5. publicstorage.com&echo 6. wc2psstaging.phelpsagency.com&echo 7. test-web2.ps.com
@echo off
set /p id=Enter 1/2/3/4 as per your target environment: 
if %id%==1 (
set env=devpsstaging.phelpsagency.com
) else if %id%==2 (
set env=psstaging.phelpsagency.com
) else if %id%==3 (
set env=test-web.ps.com
)else if %id%==4 (
set env=stage-web.ps.com
)else if %id%==5 (
set env=publicstorage.com
)else if %id%==6 (
set env=wc2psstaging.phelpsagency.com
)else if %id%==7 (
set env=test-web2.ps.com
)
echo Choose browser:&echo 1. Firefox&echo 2. Chrome&echo 3. IE 11
@echo off
set /p brwch=Enter 1/2 to choose browser:
if %brwch%==1 (
set brw=firefox
) else if %brwch%==2 (
set brw=chrome
)else if %brwch%==3 (
set brw=IE
)
cd..
mvn clean test -DtestngXml=AllTestng.xml -DoutputPath=../Dropbox/Test-Reports/${env}/${timestamp}(${browser}) -Denv=%env% -Dbrowser=%brw%