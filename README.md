# Unix Shell in Java

A Unix-like shell built in pure Java. Supports built-in commands, external command execution, multi-command pipelines, and I/O redirection.

---

## Features

- Interactive REPL loop
- Built-in commands — `cd`, `pwd`, `exit`
- External command execution via `ProcessBuilder`
- Multi-command pipelines via `|` with concurrent stream piping
- I/O redirection — `>`, `>>`, `<`
- Graceful error handling and resource management

---

## Project Structure

```
src/main/java/com/priyanshu/shell/
├── Main.java
├── Shell.java
├── parser/
│   ├── Parser.java
│   └── ParsedCommand.java
├── executor/
│   └── PipelineExecutor.java
└── commands/
    ├── Command.java
    ├── builtin/
    │   ├── CdCommand.java
    │   ├── PwdCommand.java
    │   └── ExitCommand.java
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
$ echo hello world
$ ls | grep src
$ ls | grep java | wc -l
$ grep java < input.txt
$ echo hello > out.txt
$ echo world >> out.txt
$ exit
```

---

## Pipelines

Commands are chained via `|`. Each process runs concurrently, with output streamed to the next process's input via a dedicated pipe thread to avoid deadlocks.

```
$ ls -la | grep java | wc -l
```

## I/O Redirection

```
$ echo hello > out.txt       # overwrite
$ echo world >> out.txt      # append
$ sort < input.txt           # read from file
```

Redirection is supported for external commands. Built-in commands (`cd`, `pwd`) write directly to the terminal.
