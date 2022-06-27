# Stack calculator

This stack calculator takes as a command-line argument the name of the file containing the commands. If there is no argument, then the standard input stream is used to read commands. It is based on the "factory method" pattern.
The following set of commands is implemented:

* **"#"** - a line with a comment.

* **POP**, **PUSH** — remove/put the number from / on the stack(a).

* **+**, **-** , * , **/**, **SQRT** – arithmetic operations. Use one or two top elements of the stack, remove them from the stack, placing the result back

* **PRINT** — prints the top element of the stack (without deleting it).

* **DEFINE** — set the parameter value. In the future, this value is used instead of the parameter.

Example (output 2):
*DEFINE* a 4
*PUSH* a
*SQRT*
*PRINT*


