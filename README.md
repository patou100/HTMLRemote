# HTMLRemote

This tool is used to simulate keyboard from a distant device.

1-Start the tool on local computer</br>
2-From distant device simulate keys with HTTP url: </br>
&nbsp;&nbsp;&nbsp;&nbsp;http://xxx.xxx.xxx.xxx:8090/api.pw?page=SimulKey&keyboard=alt+tab ==> This will simulate an alt+tab on xxx.xxx.xxx.xxx</br>
&nbsp;&nbsp;&nbsp;&nbsp;http://xxx.xxx.xxx.xxx:8090/api.pw?page=SimulKey&keyboard=up ==> This will simulate an hit on upper keyboard key</br>
&nbsp;&nbsp;&nbsp;&nbsp;http://xxx.xxx.xxx.xxx:8090/api.pw?page=SimulKey&keyboard=SHIFT+i ==> This will simulate an hit on Shift+i: I</br>
&nbsp;&nbsp;&nbsp;&nbsp;http://xxx.xxx.xxx.xxx:8090/api.pw?page=SimulKey&keyboard=CTRL+s ==> This will simulate an hit on Ctrl+s</br>
&nbsp;&nbsp;&nbsp;&nbsp;http://xxx.xxx.xxx.xxx:8090/api.pw?page=SimulKey&keyboard=F1 ==> This will simulate an hit on F1</br>

## Next development
Provide capability to launch pgm with HTTP request</br>
&nbsp;&nbsp;&nbsp;&nbsp;http://xxx.xxx.xxx.xxx:8090/api.pw?page=Launch&application=notepad ==> This should execute notepad</br>
