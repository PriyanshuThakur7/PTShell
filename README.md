# Unix Shell in Java

A Unix-like shell built in pure Java. Supports built-in commands, external command execution, multi-command pipelines, I/O redirection, environment variables, and command history.

---

## Features

-Interactive REPL loop
-Built-in commands — cd, pwd, exit, export, history
-External command execution via ProcessBuilder
-Multi-command pipelines via | with concurrent stream piping
-I/O redirection — >, >>, <
-Environment variable expansion — $VAR, $HOME, $PATH
-Custom variable definition via export
-Command history with 100-command session cap

---

## Project Structure



```
src/main/java/com/priyanshu/shell/
├── Main.java
├── Shell.java
├── parser/
│   ├── Parser.java
│   ├── ParsedCommand.java
│   └── Expander.java
├── executor/
│   └── PipelineExecutor.java
└── commands/
    ├── Command.java
    ├── builtin/
    │   ├── CdCommand.java
    │   ├── PwdCommand.java
    │   ├── ExitCommand.java
    │   ├── ExportCommand.java
    │   └── HistoryCommand.java
    └── external/
        └── ExternalCommand.java
```

---

## Prerequisites

- Java 21+
- Maven
- Linux / macOS / WSL

---

## Build & Run

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.priyanshu.shell.Main"
```

---

## Usage

```
$ ls -la
$ cd /tmp
$ pwd
$ echo $HOME
$ export NAME=Priyanshu
$ echo Hello $NAME
$ ls | grep src
$ ls | grep java | wc -l
$ grep java < input.txt
$ echo hello > out.txt
$ echo world >> out.txt
$ history
$ exit
```

---

## Pipelines

Commands are chained via `|`. Each process runs concurrently, with output streamed to the next process's input via a dedicated pipe thread to avoid deadlocks.

```
$ ls -la | grep java | wc -l
```

## I/O Redirection

Redirection is supported for external commands. Built-in commands (`cd`, `pwd`) write directly to the terminal.

```
$ echo hello > out.txt       # overwrite
$ echo world >> out.txt      # append
$ sort < input.txt           # read from file
```

## Environment Variables

Custom variables defined via export take precedence over OS environment variables.

```
$ export NAME=Priyanshu
$ echo Hello $NAME
$ echo $HOME
$ echo $PATH
```

##Command History

Tracks up to 100 commands per session. Records what was typed, not the expanded form.

```
$ history
1 export NAME=Priyanshu
2 echo Hello $NAME
3 history
```
