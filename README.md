#CS63637P2

##Design
this project consists of two parts, which are front end and
back end.

### Front End
The front end of project is used to collect parameters and fields value at method entry.
It utilized ASM Byte Code manipulation and instrumentation to gather information of the program under Junit Test.
After analyzing the program under tests by front end, the tool will automatically generate a data trace file which can be used in back end.

### Back End
The back end of project is used to infer the invariant pattern based on the data trace file collected in the front end.
