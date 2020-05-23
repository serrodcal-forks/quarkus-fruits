# Vegeta load test

[Vegeta](https://github.com/tsenart/vegeta) is a versatile HTTP load testing tool
built out of a need to drill HTTP services with a constant request rate. It can
be used both as a command line utility and a library.

## Install it

Follow this [page](https://github.com/tsenart/vegeta#install) to install Vegeta
load testing tool.

## Run tests

Once your application is up and running, attack your application with:
```
vegeta attack -duration=180s -targets=targets.txt | tee results.bin | vegeta report
```

Then, generate report with:
```
vegeta report -type=json results.bin > metrics.json
```

Finally, generate HTML page to show report with:
```
cat results.bin | vegeta plot > plot.html
```

Optionally, you can generate a report in console with:
```
cat results.bin | vegeta report -type="hist[0,100ms,200ms,300ms]"
```

# Results

* JVM:
```
Requests      [total, rate, throughput]         9000, 50.01, 50.01
Duration      [total, attack, wait]             3m0s, 3m0s, 1.16ms
Latencies     [min, mean, 50, 90, 95, 99, max]  375.411µs, 1.257ms, 1.069ms, 1.483ms, 1.635ms, 2.484ms, 261.781ms
Bytes In      [total, mean]                     764693, 84.97
Bytes Out     [total, mean]                     211500, 23.50
Success       [ratio]                           100.00%
Status Codes  [code:count]                      200:4501  202:4499  
Error Set:
```

* JVM (using `-Xmx18m`):
```
Requests      [total, rate, throughput]         9000, 50.01, 42.31
Duration      [total, attack, wait]             3m30s, 3m0s, 30.001s
Latencies     [min, mean, 50, 90, 95, 99, max]  357.74µs, 390.77ms, 1.204ms, 2.066ms, 4.278ms, 30.001s, 30.005s
Bytes In      [total, mean]                     755115, 83.90
Bytes Out     [total, mean]                     208774, 23.20
Success       [ratio]                           98.71%
Status Codes  [code:count]                      0:116  200:4443  202:4441  
Error Set:
```
