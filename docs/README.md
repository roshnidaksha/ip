# Planit User Guide

```
   +------------------------------------------------------------+
   |       _______   __                      __    __           |
   |      /       \ /  |                    /  |  /  |          |
   |      $$$$$$$  |$$ |  ______   _______  $$/  _$$ |_         |
   |      $$ |__$$ |$$ | /      \ /       \ /  |/ $$   |        |
   |      $$    $$/ $$ | $$$$$$  |$$$$$$$  |$$ |$$$$$$/         |
   |      $$$$$$$/  $$ | /    $$ |$$ |  $$ |$$ |  $$ | __       |
   |      $$ |      $$ |/$$$$$$$ |$$ |  $$ |$$ |  $$ |/  |      |
   |      $$ |      $$ |$$    $$ |$$ |  $$ |$$ |  $$  $$/       |
   |      $$/       $$/  $$$$$$$/ $$/   $$/ $$/    $$$$/        |
   |                                                            |
   +------------------------------------------------------------+
   
               Welcome to Planit, task management system!
   ```

Planit is a Command Line Interface (CLI) based task management application designed to help users organize their daily 
tasks efficiently.

## Table of Contents
1. [Getting Started](#getting-started)
2. [Command Summary](#command-summary)
3. [Features](#features)
   - [1. View User Guide](#1-view-user-guide-help)
   - [2. Listing all tasks](#2-listing-all-tasks-list)
   - [3. Adding todos](#3-adding-todos-todo)
   - [4. Adding deadlines](#4-adding-deadlines-deadline)
   - [5. Adding events](#5-adding-events-event)
   - [6. Marking tasks as done](#6-marking-tasks-as-done-mark)
   - [7. Unmarking tasks as not done](#7-unmarking-tasks-as-not-done-unmark)
   - [8. Deleting tasks](#8-deleting-tasks-delete)
   - [9. Finding tasks using keyword](#9-finding-tasks-using-keyword-find)
   - [10. Finding tasks using date](#10-finding-tasks-using-date-date)
   - [11. Exiting the application](#11-exiting-the-application-bye)
4. [Date and Time Format](#date-and-time-format)
5. [File Storage](#file-storage)

## Getting Started

1. Ensure you have Java `17` or above installed on your computer.
2. Download the latest `.jar` from [here](https://github.com/roshnidaksha/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your TaskList.
4. Open a command terminal, `cd` into the folder you put the jar file in and run the following:
```
java -jar planit.jar
```

## Command Summary

| Command                                           | Parameters                                             |
|---------------------------------------------------|--------------------------------------------------------|
| [`help`](#1-view-user-guide-help)                 | `[command]`                                            |
| [`list`](#2-listing-all-tasks-list)               | `None`                                                 |
| [`todo`](#3-adding-todos-todo)                    | `<task description>`                                   |
| [`deadline`](#4-adding-deadlines-deadline)        | `<task description> /by <date>`                        |
| [`event`](#5-adding-events-event)                 | `<task description> /from <start date> /to <end date>` |
| [`mark`](#6-marking-tasks-as-done-mark)           | `<task type><task index>`                              |
| [`unmark`](#7-unmarking-tasks-as-not-done-unmark) | `<task type><task index>`                              |
| [`delete`](#8-deleting-tasks-delete)              | `<task type><task index>`                              |
| [`find`](#9-finding-tasks-using-keyword-find)     | `<keyword>`                                            |
| [`date`](#10-finding-tasks-using-date-date)       | `/on <date>`                                           |
| [`bye`](#11-exiting-the-application-bye)          | `None`                                                 |


## Features

> [!TIP]
> Use `help` command to view the list of available commands.

> [!NOTE]
> Words enclosed within `<>` are placeholders for user input.

> [!NOTE]
> Words enclosed within `[]` are optional parameters.

---
### 1. View User Guide: **`help`**

**Syntax:** `help`

Displays the user guide of all available list of commands.

**Syntax:** `help <command>`

Displays the user guide of the specified command.

**Example:**
```
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
> help todo

todo: Adds a new todo task to your list
Format: todo <task description>
Example: todo prepare for CS2113
This will add task [T][ ] prepare for CS2113 to your list of tasks
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
```
---
### 2. Listing all tasks: **`list`**

**Syntax:** `list`

Displays all tasks in the task list.

**Example:**
```
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
> list

Here is a list of your tasks:
TODO:
1. [T][ ] read book
DEADLINE:
1. [D][X] cs2113 quiz (by: Feb 22 2025)
EVENT:
1. [E][ ] do cs2113 coursemology (from: Feb 23 2025 to: Feb 23 2025)
2. [E][ ] cs2113 team meeting (from: Mar 15 2025 9:00 am to: Mar 15 2025 11:00 am)
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
```
---
### 3. Adding todos: **`todo`**

**Syntax:** `todo <task description>`

Adds a new todo task to your list.

**Example:**
```
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
> todo submit user guide

Successfully added todo task:
[T][ ] submit user guide
You have 5 tasks in the list
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
```
---
### 4. Adding deadlines: **`deadline`**

**Syntax:** `deadline <task description> /by <date>`

Adds a new deadline task to your list.

**Example:**
```
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
> deadline Add function to save tasks /by Mar 1 2025

Successfully added deadline task:
[D][ ] Add function to save tasks (by: Mar 01 2025)
You have 6 tasks in the list
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
```
---
### 5. Adding events: **`event`**

**Syntax:** `event <task description> /from <start date> /to <end date>`

Adds a new event task to your list.

**Example:**
```
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
> event watch lec videos /from 26/2/2025 10:00 am /to 2025/2/26 11:00

Successfully added event task:
[E][ ] watch lec videos (from: Feb 26 2025 10:00 am to: Feb 26 2025 11:00 am)
You have 7 tasks in the list
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
```
---
### 6. Marking tasks as done: **`mark`**

**Syntax:** `mark <task type><task index>`

Marks the task at the specified index as done.

**Example:**
```
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
> mark t1

Successfully marked task as done
[T][X] read book
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
```
---
### 7. Unmarking tasks as not done: **`unmark`**

**Syntax:** `unmark <task type><task index>`

Unmarks the task at the specified index as not done.

**Example:**
```
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
> unmark t1

Successfully marked task as not done
[T][ ] read book
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
```
---
### 8. Deleting tasks: **`delete`**

**Syntax:** `delete <task type><task index>`

Deletes the task at the specified index.

**Example:**
```
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
> delete t1

Successfully deleted task: [T][ ] read book
You have 6 tasks in the list
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
```
---
### 9. Finding tasks using keyword: **`find`**

**Syntax:** `find <keyword>`

Finds all tasks that contain the specified keyword.

**Example:**
```
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
> find cs2113

Here is a list of your tasks:
DEADLINE:
1. [D][X] cs2113 quiz (by: Feb 22 2025)
EVENT:
1. [E][ ] do cs2113 coursemology (from: Feb 23 2025 to: Feb 23 2025)
2. [E][ ] cs2113 team meeting (from: Mar 15 2025 9:00 am to: Mar 15 2025 11:00 am)
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
```
---
### 10. Finding tasks using date: **`date`**

**Syntax:** `date /on <date>`

Finds all tasks that occur on the specified date.

**Example:**
```
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
> date /on Feb 22 2025

Here is a list of your tasks:
DEADLINE:
1. [D][X] cs2113 quiz (by: Feb 22 2025)
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
```
---
### 11. Exiting the application: **`bye`**

**Syntax:** `bye`

Exits the application.

**Example:**
```
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
> bye
================== Tasks Saved successfully! ================
Hope you took note of some important tasks! Bye!
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
```
---

## Date and Time Format

Planit allows various date and time formats.

Below are some examples of valid date and time formats:

- `MMM d yyyy` - `Nov 2 2025`
- `yyyy-M-d` - `2025-2-11`, `2025-02-11`
- `yyyy/M/d` - `2025/2/11`, `2025/02/11`
- `d-M-yyyy` - `11-2-2025`, `11-02-2025`
- `d/M/yyyy` - `11/2/2025`, `11/02/2025`
- `MMM d yyyy[ H:mm]` - `Nov 2 2025 10:00`
- `MMM d yyyy[ h:mm a]` - `Nov 2 2025 10:00 am`
- `yyyy-M-d[ H:mm]` - `2025-2-11 10:00`
- `yyyy-M-d[ h:mm a]` - `2025-2-11 10:00 am`
- `yyyy/M/d[ H:mm]` - `2025/2/11 10:00`
- `yyyy/M/d[ h:mm a]` - `2025/2/11 10:00 am`
- `d-M-yyyy[ H:mm]` - `11-2-2025 10:00`
- `d-M-yyyy[ h:mm a]` - `11-2-2025 10:00 am`
- `d/M/yyyy[ H:mm]` - `11/2/2025 10:00`
- `d/M/yyyy[ h:mm a]` - `11/2/2025 10:00 am`
---
## File Storage

Planit stores the tasks in a file named `tasks.txt` at `HOME/Planit/data/tasks.txt`.

This allows users to access their tasks even after restarting the application.