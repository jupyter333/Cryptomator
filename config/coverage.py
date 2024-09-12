import csv

def computeCoverage(fileList):
    """Parses one or more jacoco.csv files and computes code coverage percentages."""
    missed = 0
    covered = 0
    missedBranches = 0
    coveredBranches = 0
    
    for filename in fileList:
        try:
            with open(filename, newline='') as csvfile:
                jacocoReader = csv.reader(csvfile)
                for i, row in enumerate(jacocoReader):
                    if i > 0:  # Skip header
                        missed += int(row[3])
                        covered += int(row[4])
                        missedBranches += int(row[5])
                        coveredBranches += int(row[6])
        except FileNotFoundError:
            print(f"File not found: {filename}")
            return (0, 0)
        except Exception as e:
            print(f"Error processing file {filename}: {str(e)}")
            return (0, 0)
    
    return (
        calculatePercentage(covered, missed),
        calculatePercentage(coveredBranches, missedBranches)
    )

def calculatePercentage(covered, missed):
    """Calculates the coverage percentage."""
    if missed == 0 and covered == 0:
        return 1
    return covered / (covered + missed)

def main(jacocoCsvFile):
    coverage, branchCoverage = computeCoverage([jacocoCsvFile])
    """Return coverage percentage to check against the previous coverage."""
    return round(coverage * 100, 2)

if __name__ == "__main__":
    import sys
    jacocoCsvFile = sys.argv[1]
    result = main(jacocoCsvFile)
    print(result)
