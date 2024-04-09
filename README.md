1.Main-Class: lms.LibraryManagementSystemApplication

2.support below command
list
add "bookName" "author" <inventory:0-9>
borrow "bookName" "author"
delete "bookName" "author"
login username password
register role(admin or user) username password
return "bookName" "author"
search "bookName" "author"

3.command line parameter regex
bookName: [A-Za-z0-9\s\-\.]+
author: [A-Za-z\s\-\.]+
inventory:[0-9]
role：admin or user
username：[A-Za-z\s\-\.]{3,20}
password：[A-Za-z0-9\s\-\.\!\@\#\$\%\^\&\*]{3,15}
