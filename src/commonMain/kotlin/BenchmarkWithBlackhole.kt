package org.example

import kotlinx.benchmark.*

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(BenchmarkTimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
open class BenchmarkWithBlackhole {
    @Benchmark
    fun a(blackhole: Blackhole) {
        blackhole.consume(1)
    }

    @Benchmark
    fun b(blackhole: Blackhole) {
        blackhole.consume(2)
    }

    @Benchmark
    fun c(blackhole: Blackhole) {
        blackhole.consume(3)
    }
}
