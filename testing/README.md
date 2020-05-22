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
Duration      [total, attack, wait]             3m0s, 3m0s, 631.899µs
Latencies     [min, mean, 50, 90, 95, 99, max]  430.166µs, 1.326ms, 761.51µs, 1.128ms, 1.391ms, 2.604ms, 406.984ms
Bytes In      [total, mean]                     764552, 84.95
Bytes Out     [total, mean]                     211500, 23.50
Success       [ratio]                           100.00%
Status Codes  [code:count]                      200:4501  202:4499  
Error Set:
```

* Native:
```
Requests      [total, rate, throughput]         9000, 50.00, 50.00
Duration      [total, attack, wait]             3m0s, 3m0s, 724.749µs
Latencies     [min, mean, 50, 90, 95, 99, max]  479.76µs, 784.629µs, 736.551µs, 1.032ms, 1.141ms, 1.393ms, 9.706ms
Bytes In      [total, mean]                     764975, 85.00
Bytes Out     [total, mean]                     211500, 23.50
Success       [ratio]                           100.00%
Status Codes  [code:count]                      200:4501  202:4499  
Error Set:
```
