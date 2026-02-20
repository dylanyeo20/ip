# Dylan's Chatbot User Guide

![Product Screenshot](./Ui.png)

**Dylan** is a lightweight CLI chatbot that helps you manage your tasks, deadlines, and upcoming events.
Simply add your items and let Dylan handle the tracking for you!

---

## Features

---

## Viewing Tasks

**Command:** `list`  
**Description:** Displays all tasks and events in your list.

**Example:**

```
list
```

---

## Adding Tasks and Events

### Add To-Do Task

**Command:** `todo <task name>`
**Description:** Adds a new to-do task.

**Example:**

```
todo Buy groceries
```

---

### Add Deadline

**Command:** `deadline <task name> /by <dd/mm/yyyy hhmm>`
**Description:** Adds a task with a deadline.

**Example:**

```
deadline Submit assignment /by 25/02/2026 2359
```

---

### Add Event

**Command:**
`event <task name> /from <dd/mm/yyyy hhmm> /to <dd/mm/yyyy hhmm>`

**Description:** Adds an event with start and end times.

**Example:**

```
event Project meeting /from 26/02/2026 1400 /to 26/02/2026 1600
```

---

## Marking Tasks

### Mark as Done

**Command:** `mark <task index>`
**Description:** Marks the specified task as completed.

**Example:**

```
mark 2
```

---

### Unmark Task

**Command:** `unmark <task index>`
**Description:** Marks the specified task as not completed.

**Example:**

```
unmark 2
```

---

## 🗑️ Deleting Tasks

**Command:** `delete <task index>`
**Description:** Removes the specified task from the list.

**Example:**

```
delete 3
```

---

## Reminders

**Command:** `reminder`
**Description:** Shows tasks and events that are due or occurring within the next **7 days**.

**Example:**

```
reminder
```

---

## Notes

* Task indices start from **1**.
* Date and time format must follow: **dd/mm/yyyy hhmm**
* Commands are **case-sensitive**.
* Works on the following systems: Windows 11 Home, Kali Linux 2024.2 (Rolling) Debian-based, macOS 15.7.3



