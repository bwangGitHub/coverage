library(fields)

# This function produces the same result as the built in
# cor(x, y, method="spearman")
# and the alternative
# cor.test(x, y, method="spearman")
# and can handle arbitrary ties
spearmanRho <- function (cov, eff)
{
    # Rank the two vectors, using midranks for ties
    rankedCov = rank(cov, ties="average")
    rankedEff = rank(eff, ties="average")

    # rho with tie adjustment is equal to the Pearson
    # coefficient computed on the ranks (Gibbons 93)
    rho = cor(rankedCov, rankedEff, method="pearson")

    return (rho)
}

# This function can handle arbitrary ties in rank
# It uses formula 5 from Adler 57 ("A Modification of Kendall's Tau...")
kendallTau <- function (cov, eff)
{
    # Copy data into a new table
    n = length(cov)
    data = matrix(0, n, 2)
    data[,1] = cov
    data[,2] = eff

    # Sort the table by coverage and then effectiveness
    permutation = order(cov, eff)
    data = t(rbind(cov[permutation], eff[permutation]))

    # Compute the number of concordant and discordant pairs
    C = 0
    D = 0
    for (i in 1:(n - 1))
    {
        currentRow = data[i,]

        # Find rows where both coverage and effectiveness are larger than they are in the current row
        conc = ((data[,1] > currentRow[1]) & (data[,2] > currentRow[2]))
        
        # Increase C by the number of concordant rows (those with higher values AND indices)
        C = C + sum(conc[(i+1):n])

        # Do the same for discordant rows now
        disc = (data[,1] > currentRow[1]) & (data[,2] < currentRow[2])
        D = D + sum(disc[(i+1):n])
    }

    maxPairs = 0.5*(n^2 - n)
    print(paste(maxPairs - (C + D), "comparisons were ties out of a total of", maxPairs, "comparisons"))
    tau = (C - D) / (C + D)
    return (tau)
}

isSignificant <- function(tauVec, n)
{
    zeds = tauVec * (3 * sqrt(n * (n - 1))) / sqrt(2 * (2*n + 5))
    print("Zeds:")
    print(zeds)
    
    print("Significant at 99.9% level?")
    print(abs(zeds) >= 3.09)
}

# Using the header: suite.size, stmt.coverage, branch.coverage, mc.coverage, 
# mutants.killed, mutants.covered, num.mutants
infiles <- commandArgs(trailingOnly = T)
randomResults <- read.csv(infiles[1], header=TRUE)

#randomResults <- read.csv("~/research/test-analysis-toolkit/icse-2014-data-files/randomSuiteResultsJodaTime.csv", header=TRUE)

sizes <- sort(unique(randomResults$suite.size))
positions <- log(sizes)
randomResults$normalized.kill.score=randomResults$mutants.killed/randomResults$mutants.covered
randomResults$raw.kill.score=randomResults$mutants.killed/randomResults$num.mutants

# For RQ2, we want to compute the correlation between coverage (all three definitions) and 
# kill score (both definitions) without considering suite size
# We use Kendall's tau for this to avoid assumptions of normality
n = nrow(randomResults)
coverageTypes=list(randomResults$stmt.coverage, randomResults$branch.coverage, randomResults$mc.coverage)

print("Computing correlation between coverage and normalized kill score")
taus=lapply(coverageTypes, kendallTau, eff=randomResults$normalized.kill.score)
print("Taus are:")
print(taus)
lapply(taus, isSignificant, n=n)

print("Computing correlation between coverage and raw kill score")
taus=lapply(coverageTypes, kendallTau, eff=randomResults$raw.kill.score)
print("Taus are:")
print(taus)
lapply(taus, isSignificant, n=n)

# For RQ3, we want to compute the correlation between coverage (all three definitions) and
# kill score (both definitions) for each suite size
# That's a total of 30 to 42 correlations per project
print("Computing the correlation between coverage and normalized kill score for fixed sizes")
for (i in 1:length(sizes))
{
    print(paste("Fixing size at:", sizes[i]))
    data = randomResults[randomResults$suite.size == sizes[i],]
    coverageTypes=list(data$stmt.coverage, data$branch.coverage, data$mc.coverage)
    taus=lapply(coverageTypes, kendallTau, eff=data$normalized.kill.score)
    print(taus)
    lapply(taus, isSignificant, n=1000)
}

print("Computing the correlation between coverage and raw kill score for fixed sizes")
for (i in 1:length(sizes))
{
    print(paste("Fixing size at:", sizes[i]))
    data = randomResults[randomResults$suite.size == sizes[i],]
    coverageTypes=list(data$stmt.coverage, data$branch.coverage, data$mc.coverage)
    taus=lapply(coverageTypes, kendallTau, eff=data$raw.kill.score)
    print(taus)
    lapply(taus, isSignificant, n=1000)
}