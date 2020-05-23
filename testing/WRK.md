# Load testing with Wrk

Let's compare Quarkus JVM vs Quarkus Native using Reactive Routes and Vert.x.

```sh
$ wrk -t10 -c100 -d60s http://localhost:8080/api/v1/fruit
```

## Quarkus JVM

```
Running 1m test @ http://localhost:8080/api/v1/fruit
  10 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     9.81ms   22.04ms 510.19ms   98.24%
    Req/Sec     1.37k   434.43     4.05k    64.18%
  812731 requests in 1.00m, 131.76MB read
Requests/sec:  13524.89
Transfer/sec:      2.19MB
```

## Quarkus Native

```
Running 1m test @ http://localhost:8080/api/v1/fruit
  10 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     9.44ms   10.22ms 153.67ms   92.10%
    Req/Sec     1.31k   446.22     2.48k    77.51%
  785403 requests in 1.00m, 127.33MB read
Requests/sec:  13072.87
Transfer/sec:      2.12MB
```

## Bonus: Quarkus JVM with -Xmx18m

```
Running 1m test @ http://localhost:8080/api/v1/fruit
  10 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    12.73ms   17.62ms 438.60ms   98.80%
    Req/Sec     0.88k   200.73     1.40k    75.82%
  523453 requests in 1.00m, 84.86MB read
Requests/sec:   8710.11
Transfer/sec:      1.41MB
```
