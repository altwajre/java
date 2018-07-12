# api simulations

## hoverfly

https://hoverfly.io/
https://hoverfly.readthedocs.io/en/latest/

https://www.safaribooksonline.com/videos/oreilly-software-architecture/9781492028116/9781492028116-video318593

- Start hoverctl

```
$ hoverctl start
Hoverfly is now running

+------------+------+
| admin-port | 8888 |
| proxy-port | 8500 |
+------------+------+
```

- Capture HTTP request and response

```
$ hoverctl mode capture
Hoverfly has been set to capture mode
$ curl --proxy http://localhost:8500 http://time.jsontest.com
{
   "time": "06:29:03 PM",
   "milliseconds_since_epoch": 1531333743330,
   "date": "07-11-2018"
}
```

- Create a simulation

```
$ hoverctl export simulation.json
Successfully exported simulation to simulation.json
```

- Simulate the request

```
$ hoverctl mode simulate
Hoverfly has been set to simulate mode with a matching strategy of 'strongest'
LMDV-WHan:~ whan$ curl --proxy http://localhost:8500 http://time.jsontest.com
{
   "time": "06:29:03 PM",
   "milliseconds_since_epoch": 1531333743330,
   "date": "07-11-2018"
}
```
