package org.example

import kotlinx.benchmark.*

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(BenchmarkTimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
open class BenchmarkWithoutBlackhole {
    @Benchmark
    fun a(): Int = 1

    @Benchmark
    fun b(): Int = 2

    @Benchmark
    fun c(): Int = 3
}
