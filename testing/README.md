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
Duration      [total, attack, wait]             3m0s, 3m0s, 804.421µs
Latencies     [min, mean, 50, 90, 95, 99, max]  655.999µs, 1.468ms, 1.134ms, 1.778ms, 2.1ms, 2.98ms, 272.706ms
Bytes In      [total, mean]                     764693, 84.97
Bytes Out     [total, mean]                     211500, 23.50
Success       [ratio]                           100.00%
Status Codes  [code:count]                      200:4500  201:1  202:4499  
Error Set:
```

* Native:
```
Requests      [total, rate, throughput]         9000, 50.01, 50.01
Duration      [total, attack, wait]             3m0s, 3m0s, 1.196ms
Latencies     [min, mean, 50, 90, 95, 99, max]  621.389µs, 1.119ms, 1.041ms, 1.469ms, 1.591ms, 1.947ms, 11.78ms
Bytes In      [total, mean]                     764975, 85.00
Bytes Out     [total, mean]                     211500, 23.50
Success       [ratio]                           100.00%
Status Codes  [code:count]                      200:4500  201:1  202:4499  
Error Set:
```
