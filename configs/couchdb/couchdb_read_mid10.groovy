storageType="couchdb"
testName="couchdb_read_mid10"

/**
 * Configuration of the Voldemort ClientStore.
 */
storeFactory {
    storePort         = 5984
    database          = "test"
    document          = "test"
    retries           = 5
}

// # of client threads to start
numThreads  = 50

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
