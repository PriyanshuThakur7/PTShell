# Unix Shell in Java

A Unix-like shell built in pure Java. Supports built-in commands, external command execution, and multi-command pipelines.

## Features

- Interactive REPL loop (Read → Evaluate → Print → Loop)
- Input tokenization and parsing
- Built-in commands: `cd`, `pwd`, `exit`
- External command execution via `ProcessBuilder`
- Multi-command pipeline support (`ls | grep java | wc -l`)
- Graceful error handling and resource management

## Project Structure

```
src/main/java/com/priyanshu/shell/
├── Main.java
├── Shell.java
├── parser/
│   └── Parser.java
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

### Prerequisites

- Java 11+
- Maven
- Linux / macOS / WSL (Windows Subsystem for Linux)

### Build

mvn compile


### Run

mvn exec:java -Dexec.mainClass="com.priyanshu.shell.Main"

## Usage

```
$ ls
$ ls -la
$ pwd
$ cd /tmp
$ echo hello world
$ ls | grep src
$ ls | grep java | wc -l
$ exit
```

## Pipeline Support

Commands can be chained using the `|` operator. Output of each command is piped as input to the next using concurrent threads to avoid deadlocks.

```
$ ls -la | grep java | wc -l
```
