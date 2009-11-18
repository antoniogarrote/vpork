storageType="redis"
testName="redis_read_mid3"

/**
 * Configuration of the Redis ClientStore.
 */
storeFactory {
    storePort         = 6379
}

// # of client threads to start
numThreads  = 15

// Odds of performing a read op per iteration (1 = 100%)
readOdds    = 0.5
writeOdds   = 0.5
rewriteOdds = 0.0

// How many iterations should each thread execute?
threadIters = 4000

// How much data should be written per write operation?
dataSize    = (8 + 4) * 2000

// What function best describes our data access patterns?  This factor is
// is applied across the range of already-written records.  We choose a function
// with a long tail, since we do most of our reads at current-time, and
// not many in the recent past.
readFactor = {x -> (1.0 - x) ** 10.0}
