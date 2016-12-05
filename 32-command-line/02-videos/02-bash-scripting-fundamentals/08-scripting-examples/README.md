# Scripting by Example

## print couple processes pids and pid names with user-defined format

```
$ ps -eo pid -o comm= | head -3
   PID 
     1 systemd
     2 kthreadd

$ man ps
-e <- select all processes. Identical to -A

-o <- user-defined format.

-eo <- select all processes, and add user-defined format

- o comm= <- print pid name
EXAMPLES
Print only the name of PID 42:
          ps -q 42 -o comm=
```

## kill highest cpu usage processor

```
$ while true; do true; done & <- create an infinite loop job that uses lots of cpu
[1] 83862
$ ps -eo pcpu,pid -o comm= | sort -k1 -n -r | head -1 <- list the highest cpu usage processor; "-o comm=": list process name, "head -1": get the first item of the list
99.6  83862 bash
$ kill 83862
[1]+  Terminated              while true; do
    true;
done
$ ps -eo pcpu,pid -o comm= | sort -k1 -n -r | head -1
 0.1   3632 vmtoolsd
```
