library(ggplot2)
library(MASS)
library(grid)
library(RColorBrewer)
library(reshape)

makeBoxplot <- function(df)
{
    # Make graph
    pdf(paste("rq1-", df$short.name, ".pdf", sep=""))
    graph = ggplot()

    # Alter appearance
    sizes = sort(unique(df$size))
    graph = graph + scale_x_continuous("\nSize", trans="log10", breaks=sizes, labels=sizes)
    graph = graph + scale_y_continuous("Effectiveness\n")
    graph = graph + coord_cartesian(ylim=c(0,1))
    graph = graph + opts(aspect.ratio = 1,
                          axis.title.x = theme_text(size=11, lineheight=0.5),
                          axis.title.y = theme_text(size=11, lineheight=0.5, angle=90, hjust=0.5))

    # Add measurements as boxplots
    graph = graph + layer(data = df,
                          geom = "boxplot",
                          mapping = aes(x=size, y=kill.score, group=size))

    print(graph)
    dev.off()
}

makeGodawfulFacetedBoxplot <- function(df)
{
    df$kill.score=df$mutants.killed/df$mutants.covered

    # Make graph
    pdf("rq1.pdf")
    graph = ggplot()

    # Alter appearance
    graph = graph + scale_x_continuous("\nSize", trans="log10", breaks=sizes, labels=sizes)
    graph = graph + scale_y_continuous("Effectiveness\n")
    graph = graph + coord_cartesian(ylim=c(0,1))
    graph = graph + opts(aspect.ratio = 1,
                         panel.margin = unit(4, "mm"),
                         axis.text.x = theme_text(colour="grey50", size=9, angle=90, hjust=0, lineheight=0.9),
                         axis.text.y = theme_text(colour="grey50", size=9, lineheight=0.9),
                         axis.title.x = theme_text(size=11, lineheight=0.5),
                         axis.title.y = theme_text(size=11, lineheight=0.5, angle=90, hjust=0.5))
    
    # Add measurements as boxplots
    graph = graph + layer(data = df,
                          geom = "boxplot",
                          mapping = aes(x=suite.size, y=kill.score, group=suite.size))

    # Add a layer for each prediction line and caption
    poiTheta = c(5.221e-01,4.534e-02,7.393e-05) #R 0.78
    closureTheta = c(4.094e-01,5.491e-02,2.640e-05) # R 0.97
    hsqldbTheta = c(7.019e-01,-1.742e-02,1.946e-04) # R 0.26
    jfreeTheta = c(6.937e-01,1.876e-02,1.273e-04) #R 0.55
    jodaTheta = c(3.959e-01,7.135e-02,1.817e-06)

    # Apache POI	     
    projectName = "Apache POI"
    theta = poiTheta
    workingDF = df[df$project == projectName,]
    sizes = sort(unique(workingDF$suite.size))
    maxSize = max(sizes)
    
    # Make data frame that describes the prediction line
    predLine = data.frame(size=(seq(from = 3, to = maxSize, length = maxSize)), project=projectName)
    predLine$eff = rep(theta[1], times=length(predLine$size)) + (log(predLine$size)*theta[2] + predLine$size*theta[3])

    caption = data.frame(cap="R^2 = 0.78", max.size=maxSize, project=projectName)
    # Add regression line to graph
    graph = graph + layer(data = predLine,
                          geom = "line",
                          geom_params = list(colour="red"),
                          mapping = aes(x=size, y=eff, group=1))
    # Add caption with r^2 value
    graph = graph + layer(data = caption,
                          geom = "text",
                          mapping = aes(x=800, y=0.05, label=cap),
                          geom_params = list(size=4, colour="red"))

    # Closure
    projectName = "Closure"
    theta = closureTheta
    workingDF = df[df$project == projectName,]
    sizes = sort(unique(workingDF$suite.size))
    maxSize = max(sizes)
    
    # Make data frame that describes the prediction line
    predLine = data.frame(size=(seq(from = 3, to = maxSize, length = maxSize)), project=projectName)
    predLine$eff = rep(theta[1], times=length(predLine$size)) + (log(predLine$size)*theta[2] + predLine$size*theta[3])

    caption = data.frame(cap="R^2 = 0.97", max.size=maxSize, project=projectName)
    # Add regression line to graph
    graph = graph + layer(data = predLine,
                          geom = "line",
                          geom_params = list(colour="red"),
                          mapping = aes(x=size, y=eff, group=1))
    # Add caption with r^2 value
    graph = graph + layer(data = caption,
                          geom = "text",
                          mapping = aes(x=800, y=0.05, label=cap),
                          geom_params = list(size=4, colour="red"))

    # HSQLDB
    projectName = "HSQLDB"
    theta = hsqldbTheta
    workingDF = df[df$project == projectName,]
    sizes = sort(unique(workingDF$suite.size))
    maxSize = max(sizes)
    
    # Make data frame that describes the prediction line
    predLine = data.frame(size=(seq(from = 3, to = maxSize, length = maxSize)), project=projectName)
    predLine$eff = rep(theta[1], times=length(predLine$size)) + (log(predLine$size)*theta[2] + predLine$size*theta[3])

    caption = data.frame(cap="R^2 = 0.26", max.size=maxSize, project=projectName)
    # Add regression line to graph
    graph = graph + layer(data = predLine,
                          geom = "line",
                          geom_params = list(colour="red"),
                          mapping = aes(x=size, y=eff, group=1))
    # Add caption with r^2 value
    graph = graph + layer(data = caption,
                          geom = "text",
                          mapping = aes(x=800, y=0.05, label=cap),
                          geom_params = list(size=4, colour="red"))

    # JFreeChart
    projectName = "JFreeChart"
    theta = jfreeTheta
    workingDF = df[df$project == projectName,]
    sizes = sort(unique(workingDF$suite.size))
    maxSize = max(sizes)
    
    # Make data frame that describes the prediction line
    predLine = data.frame(size=(seq(from = 3, to = maxSize, length = maxSize)), project=projectName)
    predLine$eff = rep(theta[1], times=length(predLine$size)) + (log(predLine$size)*theta[2] + predLine$size*theta[3])

    caption = data.frame(cap="R^2 = 0.55", max.size=maxSize, project=projectName)
    # Add regression line to graph
    graph = graph + layer(data = predLine,
                          geom = "line",
                          geom_params = list(colour="red"),
                          mapping = aes(x=size, y=eff, group=1))
    # Add caption with r^2 value
    graph = graph + layer(data = caption,
                          geom = "text",
                          mapping = aes(x=800, y=0.05, label=cap),
                          geom_params = list(size=4, colour="red"))

    # Joda Time
    projectName = "Joda Time"
    theta = jodaTheta
    workingDF = df[df$project == projectName,]
    sizes = sort(unique(workingDF$suite.size))
    maxSize = max(sizes)
    
    # Make data frame that describes the prediction line
    predLine = data.frame(size=(seq(from = 3, to = maxSize, length = maxSize)), project=projectName)
    predLine$eff = rep(theta[1], times=length(predLine$size)) + (log(predLine$size)*theta[2] + predLine$size*theta[3])

    caption = data.frame(cap="R^2 = 0.89", max.size=maxSize, project=projectName)
    # Add regression line to graph
    graph = graph + layer(data = predLine,
                          geom = "line",
                          geom_params = list(colour="red"),
                          mapping = aes(x=size, y=eff, group=1))
    # Add caption with r^2 value
    graph = graph + layer(data = caption,
                          geom = "text",
                          mapping = aes(x=800, y=0.05, label=cap),
                          geom_params = list(size=4, colour="red"))
    
    # Facet on project
    graph = graph + facet_wrap(facets=~project, nrow=2)

    print(graph)
    dev.off()
}

makeBoxplotWithRegLine <- function(df, filename, theta, sigma, maxSize)
{
    # Make data frame that describes the prediction line
    predLine = data.frame(size=(seq(from = 3, to = maxSize, length = maxSize)))
    predLine$eff = rep(theta[1], times=length(predLine$size)) + (log(predLine$size)/sigma[1])*theta[2] + (sqrt(predLine$size)/sigma[2])*theta[3] + (predLine$size/sigma[3])*theta[4]

    # Make data frame that will be used to compute r^2
    sizes = sort(unique(df$size))
    predictions = rep(theta[1], times=length(sizes)) + (log(sizes)/sigma[1])*theta[2] + (sqrt(sizes)/sigma[2])*theta[3] + (sizes/sigma[3])*theta[4]
    predPearson = data.frame(size=rep(sizes, each=1000), kill.score=rep(predictions, each=1000))
    
    # Compute pearson correlation coefficient
    rVal = cor(x=df$kill.score, y=predPearson$kill.score, use="everything", method="pearson")
    caption = data.frame(cap=paste("R^2 = ", round(rVal*rVal, digits=2), sep=""), max.size=maxSize)
    
    # Make graph
    pdf(filename)
    graph = ggplot()

    # Alter appearance
    graph = graph + scale_x_continuous("\nSize", trans="log10", breaks=sizes, labels=sizes)
    graph = graph + scale_y_continuous("Effectiveness\n")
    graph = graph + coord_cartesian(ylim=c(0,1))
    graph = graph + opts(aspect.ratio = 1,
                          axis.title.x = theme_text(size=11, lineheight=0.5),
                          axis.title.y = theme_text(size=11, lineheight=0.5, angle=90, hjust=0.5))

    # Add measurements as boxplots
    graph = graph + layer(data = df,
                          geom = "boxplot",
                          mapping = aes(x=size, y=kill.score, group=size))

    # Add regression line
    graph = graph + layer(data = predLine,
                          geom = "line",
                          geom_params = list(colour="red"),
                          mapping = aes(x=size, y=eff, group=1))
    
    # Add caption with r^2 value
    graph = graph + layer(data = caption,
                          geom = "text",
                          mapping = aes(x=0.8*max.size, y=0.05, label=cap),
                          geom_params = list(size=4, colour="red"))

    print(graph)
    dev.off()
    return(graph)
}

onePanel <- function(df)
{
    df$kill.score=df$mutants.killed/df$mutants.covered
    pdf("closure-panel.pdf",8,8) 
    graph = ggplot()

    # Alter appearance
    graph = graph + scale_x_continuous("\nCoverage")
    graph = graph + scale_y_continuous("Effectiveness\n")
    graph = graph + coord_cartesian(xlim=c(0,1), ylim=c(0,1))
    graph = graph + opts(legend.position="bottom",
                         aspect.ratio = 1,
                         axis.title.x = theme_text(size=16, lineheight=0.5),
                         axis.title.y = theme_text(size=16, lineheight=0.5, angle=90, hjust=0.5),
                         axis.text.x = theme_text(colour="grey50", size=12, hjust=0, lineheight=0.9),
                         axis.text.y = theme_text(colour="grey50", size=12, hjust=0, lineheight=0.9),
                         legend.title=theme_text(size=12),
			 legend.text=theme_text(size=12))
    graph = graph + scale_colour_hue(name="Coverage Type")

    # Add data
    graph = graph + layer(data = df,
                          geom = "point",
                          geom_params = list(size=1),
                          mapping = aes(x=stmt.coverage, y=kill.score, colour="Statement coverage"))
    graph = graph + layer(data = df,
                          geom = "point",
                          geom_params = list(size=1),
                          mapping = aes(x=branch.coverage, y=kill.score, colour="Branch coverage"))
    graph = graph + layer(data = df,
                          geom = "point",
                          geom_params = list(size=1),
                          mapping = aes(x=mc.coverage, y=kill.score, colour="Modified condition coverage"))

    fuckHadley=data.frame(suite.size=c(3, 3, 3),stmt.coverage=c(-1,-1,-1),branch.coverage=c(-1,-1,-1),mc.coverage=c(-1,-1,-1), kill.score=c(-1,-1,-1), project=c("JFreeChart", "Joda Time", "HSQLDB"), coverage.type=c("Statement coverage", "Decision coverage", "Modified condition coverage"))
    
    graph = graph + layer(data=fuckHadley,
                          mapping = aes(x=stmt.coverage, y=kill.score, colour="Statement coverage"),
                          geom = "point",
                          geom_params = list(size=4))

    print(graph)
    dev.off()
}

makeScatterplot <- function(df, shouldColour)
{
    df$kill.score=df$mutants.killed/df$mutants.covered
    filenames = c("stmt", "branch", "mc")
    axisLabels = c("Statement coverage", "Branch coverage", "Modified condition coverage")
    for (i in 1:3)
    {
        # Make graph
        pdf(paste("rq2-", df$short.name, "-", filenames[i], "-norm.pdf", sep="")) 
        graph = ggplot()

        # Alter appearance
        graph = graph + scale_x_continuous(paste("\n", axisLabels[i], sep=""))
        graph = graph + scale_y_continuous("Effectiveness\n")
        graph = graph + coord_cartesian(xlim=c(0,1), ylim=c(0,1))
        graph = graph + opts(aspect.ratio = 1,
                             axis.title.x = theme_text(size=11, lineheight=0.5),
                             axis.title.y = theme_text(size=11, lineheight=0.5, angle=90, hjust=0.5))

        if (shouldColour)
        {
            graph = graph + scale_colour_hue(name="Size")

            # Add data
            graph = graph + layer(data = df,
                                  geom = "point",
                                  geom_params = list(size=1),
                                  mapping = aes_string(x=names(df)[i+1], y="kill.score", colour="as.factor(suite.size)"))
        }

        else
        { 
            # Add data
            graph = graph + layer(data = df,
                                  geom = "point",
                                  geom_params = list(size=1), #, alpha=1/5),
                                  mapping = aes_string(x=names(df)[i+1], y="kill.score"))
        }
    
        print(graph)
        dev.off()
    }
}

makeBlockScatterplot <- function(df, shouldColour)
{
        # Make graph
        pdf("rq2-poi-block.pdf") 
        graph = ggplot()

        # Alter appearance
        graph = graph + scale_x_continuous("\nBlock coverage")
        graph = graph + scale_y_continuous("Effectiveness\n")
        graph = graph + coord_cartesian(xlim=c(0,1), ylim=c(0,1))
        graph = graph + opts(aspect.ratio = 1,
                             axis.title.x = theme_text(size=11, lineheight=0.5),
                             axis.title.y = theme_text(size=11, lineheight=0.5, angle=90, hjust=0.5))

        if (shouldColour)
        {
            graph = graph + scale_colour_hue(name="Size")

            # Add data
            graph = graph + layer(data = df,
                                  geom = "point",
                                  geom_params = list(size=1),
                                  mapping = aes(x=block.coverage, y=kill.score, colour=as.factor(size)))
        }

        else
        { 
            # Add data
            graph = graph + layer(data = df,
                                  geom = "point",
                                  geom_params = list(size=1), #, alpha=1/5),
                                  mapping = aes(x=block.coverage, y=kill.score))
        }
    
        print(graph)
        dev.off()
}

makeFacetedPlot <- function(df, filename)
{
    df$kill.score=df$mutants.killed/df$mutants.covered

    # Reshape data frame
    newDF = rbind(df, df, df)
    newDF$coverage = c(df$stmt.coverage, df$branch.coverage, df$mc.coverage)
    numObvs = nrow(df)
    newDF$coverage.type = c(rep("Statement coverage", numObvs),
                            rep("Decision coverage", numObvs),
                            rep("Modified condition coverage", numObvs))
    newDF$stmt.coverage = NULL
    newDF$branch.coverage = NULL
    newDF$mc.coverage = NULL
    
    # Make graph
    pdf(filename)
    graph = ggplot() #data=newDF, aes(x=coverage, y=kill.score, colour=coverage.type))

    # Alter appearance
    graph = graph + scale_x_continuous("\nCoverage")
    graph = graph + scale_y_continuous("Effectiveness\n")
    graph = graph + coord_cartesian(xlim=c(0,1), ylim=c(0,1))
    graph = graph + opts(legend.position="bottom",
                         panel.margin = unit(4, "mm"),
                         axis.text.x = theme_text(colour="grey50", size=8, angle=90, hjust=0, lineheight=0.9),
                         axis.text.y = theme_text(colour="grey50", size=8, lineheight=0.9),
                         axis.title.x = theme_text(size=11, lineheight=0.5),
                         axis.title.y = theme_text(size=11, lineheight=0.5, angle=90, hjust=0.5))
    
    
    # Add data
    graph = graph + scale_colour_hue(name="Coverage Type")
    graph = graph + layer(data = newDF,
                          mapping = aes(x=coverage, y=kill.score, colour=coverage.type),
                          geom = "point",
                          geom_params = list(shape="."))
    
    fuckHadley=data.frame(suite.size=c(3, 3, 3), coverage=c(-1,-1,-1), kill.score=c(-1,-1,-1), project=c("JFreeChart", "Joda Time", "HSQLDB"), coverage.type=c("Statement coverage", "Decision coverage", "Modified condition coverage"))
    
    graph = graph + layer(data=fuckHadley,
                          mapping = aes(x=coverage, y=kill.score, colour=coverage.type),
                          geom = "point",
                          geom_params = list(size=4))
    graph = graph + guides(colour=guide_legend())
    graph = graph + facet_grid(facets=suite.size~project, margins=TRUE)

    
    

    # Add NA labels
#    NAlabels = data.frame(size=c(1000, 3000, 3000, 3000),
#                          project=c("HSQLDB", "HSQLDB", "JFreeChart", "Apache POI"),
#                          text=rep("N/A", 4),
#                          xPos=rep(0.5, 4),
#                          yPos=rep(0.5, 4))
#    graph = graph + layer(data=NAlabels,
#                          geom="text",
#                          geom_params=list(size=4, colour="grey50"),
#                          mapping=aes(x=xPos, y=yPos, label=text))
    
    print(graph)
    dev.off()  
}

# Facets the data for one project by size, making two rows
makeWrappedPlot <- function(df, filename)
{
    pdf(filename)
    graph = ggplot(df, aes(x=block.coverage, y=kill.score))
    
    # Alter appearance
    graph = graph + scale_x_continuous("\nBlock coverage")
    graph = graph + scale_y_continuous("Effectiveness\n")
    graph = graph + coord_cartesian(xlim=c(0,1), ylim=c(0,1))
    graph = graph + opts(panel.margin = unit(4, "mm"),
                         axis.text.x = theme_text(colour="grey50", size=9, angle=90, hjust=0, lineheight=0.9),
                         axis.text.y = theme_text(colour="grey50", size=9, lineheight=0.9),
                         axis.title.x = theme_text(size=11, lineheight=0.5),
                         axis.title.y = theme_text(size=11, lineheight=0.5, angle=90, hjust=0.5),
                         aspect.ratio=1)

    # Add data
    graph = graph + layer(geom = "point",
                          geom_params = list(shape="."))
    graph = graph + facet_wrap(facets=~size, nrow=2)
    
    print(graph)
    dev.off()
}

makeHeatmap <- function(filename, theta, sigma)
{
    # Make a dataframe with the input features
    size = seq(from = 1, to = 1000, length = 100)
    cov = seq(from = 0.01, to = 1, length = 100)
    df = expand.grid(size=size, cov=cov)

    # Add a prediction column
    df$eff = (log(df$size)/sigma[1])*theta[1] + (sqrt(df$size)/sigma[2])*theta[2] + (df$size/sigma[3])*theta[3] + (log(df$cov)/sigma[4])*theta[4] + (sqrt(df$cov)/sigma[5])*theta[5] + (df$cov/sigma[6])*theta[6]

    # Apply upper and lower bounds
    df$eff = (df$eff>1)*1 + (df$eff<=1)*df$eff
    df$eff = (df$eff<0)*0 + (df$eff>=0)*df$eff

    # Make graph
    pdf(filename)
    graph = ggplot()

    # Alter appearance
    graph = graph + scale_x_continuous("\nSize")
    graph = graph + scale_y_continuous("Block coverage\n")
    graph = graph + coord_cartesian(xlim=c(0,1000), ylim=c(0,1))
    graph = graph + scale_fill_gradient("Effectiveness", limits=c(0,1), low = "black", high = "white", breaks=c(0, 0.2, 0.4, 0.6, 0.8, 1), labels=c(0, 0.2, 0.4, 0.6, 0.8, 1))
    graph = graph + opts(aspect.ratio = 1,
                         axis.title.x = theme_text(size=11, lineheight=0.5),
                         axis.title.y = theme_text(size=11, lineheight=0.5, angle=90, hjust=0.5))
    theme_set(theme_bw())
    
    # Add data
    graph = graph + layer(data=df,
                          geom="tile",
                          mapping=aes(x=size, y=cov, fill=eff))

    print(graph)
    dev.off()
}

makeSingleMethodHeatmap <- function(df, filename, breakVec)
{
    # Make graph
    pdf(filename)
    #graph = ggplot()


    graph <- ggplot(df, aes(kill.score, kill.score.denom))
    graph <- graph + stat_binhex(bins = 30)

    
    # Alter appearance
    graph = graph + scale_x_continuous("\nEffectiveness")
    graph = graph + scale_y_continuous("Number of covered mutants\n")
    hexsize = max(df$kill.score.denom)/30.0
    graph = graph + coord_cartesian(xlim=c(-0.05,1.05), ylim=c(-hexsize,max(df$kill.score.denom)+hexsize))
    #graph = graph + scale_fill_gradient("Count", limits=c(0,150), breaks=c(0, 50, 100, 150), labels=c(0, 50, 100, 150))
    graph = graph + scale_fill_gradient("Count", low="blue", high="red", limits=c(min(breakVec), max(breakVec)), breaks=breakVec, labels=breakVec)
    graph = graph + opts(aspect.ratio = 1,
                         axis.title.x = theme_text(size=11, lineheight=0.5),
                         axis.title.y = theme_text(size=11, lineheight=0.5, angle=90, hjust=0.5))

    ## graph = graph + layer(data = df,
    ##                       stat = "stat_binhex",
    ##                       stat_params = list(bins=30), #, alpha=1/5),
    ##                       mapping = aes(x=kill.score, y=num))

    print(graph)
    dev.off()
}

makeHistogram <- function(df, filename)
{
    pdf(filename)
    graph = ggplot(df, aes(x=branch.coverage))
    
    # Alter appearance
    graph = graph + scale_x_continuous("\nBranch coverage")
    graph = graph + scale_y_continuous("Count\n")
    graph = graph + coord_cartesian(xlim=c(0,1)) #, ylim=c(0,1))
    graph = graph + opts(axis.text.x = theme_text(colour="grey50", size=9, angle=90, hjust=0, lineheight=0.9),
                         axis.text.y = theme_text(colour="grey50", size=9, lineheight=0.9),
                         axis.title.x = theme_text(size=11, lineheight=0.5),
                         axis.title.y = theme_text(size=11, lineheight=0.5, angle=90, hjust=0.5),
                         aspect.ratio=1)

    # Add data
    graph = graph + layer(geom = "histogram") #,
                          #geom_params = list(shape="."))
    
    print(graph)
    dev.off()
}

##### SET UP DATA FRAMES #####
## suite.size,stmt.coverage,branch.coverage,mc.coverage,mutants.killed,mutants.covered,num.mutants
joda <- read.csv("~/research/test-analysis-toolkit/test-subjects/joda-time-results/randomSuiteResults.csv", header=TRUE)
jfree <- read.csv("~/research/test-analysis-toolkit/test-subjects/jfreechart-results/randomSuiteResults.csv", header=TRUE)
poi <- read.csv("~/research/test-analysis-toolkit/test-subjects/apache-poi-results/randomSuiteResults.csv", header=TRUE)
hsqldb <- read.csv("~/research/test-analysis-toolkit/test-subjects/hsqldb-results/randomSuiteResults.csv", header=TRUE)
closure <- read.csv("~/research/test-analysis-toolkit/test-subjects/closure-results/randomSuiteResults.csv", header=TRUE)

joda$project <- "Joda Time"
jfree$project <- "JFreeChart"
poi$project <- "Apache POI"
hsqldb$project <- "HSQLDB"
closure$project <- "Closure"

joda$short.name <- "joda"
jfree$short.name <- "jfree"
poi$short.name <- "poi"
hsqldb$short.name <- "hsqldb"
closure$short.name <- "closure"

allProjects <- rbind(joda, jfree, poi, hsqldb, closure)

##### MAKE GRAPHS FOR RESEARCH QUESTION 1 #####
makeResearchQuestion1Graphs <- function()
{
    ## poiThetaRQ1 = c(0.537614, 0.020892, 0.179763, -0.090499)
    ## closureThetaRQ1 = c(0.435652, 0.070430, 0.146574, -0.070421)
    ## hsqldbThetaRQ1 = c(0.70250, -0.02818, 0.00000, 0.02013)
    ## jfreeThetaRQ1 = c(0.714212, -0.058167, 0.248983, -0.117529)
    ## jodaThetaRQ1 = c(0.410247, 0.129858, 0.092806, -0.060311)

    ## poiSigmaRQ1 = c(1.9742, 10.3469, 354.5169)
    ## closureSigmaRQ1 = c(2.3029, 17.9032, 1019.9449)
    ## hsqldbSigmaRQ1 = c(1.6285, 5.6373, 111.1499)
    ## jfreeSigmaRQ1 = c(1.9742, 10.3469, 354.5169)
    ## jodaSigmaRQ1 = c(2.3029, 17.9032, 1019.9449)

    ## makeBoxplotWithRegLine(poi, "rq1-poi.pdf", poiThetaRQ1, poiSigmaRQ1, 1000)
    ## makeBoxplotWithRegLine(closure, "rq1-closure.pdf", closureThetaRQ1, closureSigmaRQ1, 3000)
    ## makeBoxplotWithRegLine(hsqldb, "rq1-hsqldb.pdf", hsqldbThetaRQ1, hsqldbSigmaRQ1, 300)
    ## makeBoxplotWithRegLine(jfree, "rq1-jfree.pdf", jfreeThetaRQ1, jfreeSigmaRQ1, 1000)
    ## makeBoxplotWithRegLine(joda, "rq1-joda.pdf", jodaThetaRQ1, jodaSigmaRQ1, 3000)

    makeGodawfulFacetedBoxplot(allProjects)
}

#makeResearchQuestion1Graphs()

##### MAKE GRAPHS FOR RESEARCH QUESTION 2 #####
makeResearchQuestion2Graphs <- function()
{
    makeScatterplot(joda, TRUE)
    makeScatterplot(jfree, TRUE)
    makeScatterplot(hsqldb, TRUE)
    makeScatterplot(poi, TRUE)
    makeScatterplot(closure, TRUE)
}

makeResearchQuestion2Graphs()
#onePanel(closure[closure$suite.size == 100,])

##### MAKE GRAPHS FOR RESEARCH QUESTION 3 #####
#makeFacetedPlot(allProjects, "rq3.pdf")

##### MAKE GRAPHS FOR SINGLE METHOD RESULTS #####
#jodaSingle <- read.csv("/media/batdrive/test-analysis-toolkit/test-subjects/joda-time-results/moddedMethod.csv", header=TRUE) #size,block.coverage,kill.score
#hsqldbSingle <- read.csv("/media/batdrive/test-analysis-toolkit/test-subjects/hsqldb-results/singleMethodResults.csv", header=TRUE)
#poiSingle <- read.csv("/media/batdrive/test-analysis-toolkit/test-subjects/apache-poi-results/singleMethodResults.csv", header=TRUE)
#jfreeSingle <- read.csv("/media/batdrive/test-analysis-toolkit/test-subjects/jfreechart-results/singleMethodResults.csv", header=TRUE)

#makeSingleMethodHeatmap(jodaSingle, "single-test-joda.pdf", c(0,50,100,150))
#makeSingleMethodHeatmap(hsqldbSingle, "single-test-hsqldb.pdf", c(0,50,100,150,200))
#makeSingleMethodHeatmap(poiSingle, "single-test-poi.pdf", c(0,50,100,150,200,250,300))
#makeSingleMethodHeatmap(jfreeSingle, "single-test-jfree.pdf", c(0,50,100,150))

#makeHistogram(jodaSingle, "joda-single-hist-stmt.pdf")
#makeHistogram(jodaSingle, "joda-single-hist-branch.pdf")
#makeHistogram(jodaSingle, "joda-single-hist.pdf")

##### LINEAR REGRESSION RESULTS #####
## poiTheta = c(0.39218, -0.59201, 0.30948, 0.00000, 0.06884, 0.02317)
## hsqldbTheta = c(0.037371, 0.008718, 0.016340, -0.023812, 0.000000, 0.037183)
## jfreeTheta = c(0.00000, 0.18785, -0.09439, -0.08100, 0.08443, 0.00000)
## jodaTheta = c(0.14599, -0.11843, 0.00000, -0.13164, -0.10509, 0.34712)

## poiSigma = c(1.97424, 10.34689, 354.51685, 0.47274, 0.12860, 0.16044)
## hsqldbSigma = c(1.6285, 5.6373, 111.15, 0.096736, 0.025912, 0.028002)
## jfreeSigma = c(1.97424, 10.34689, 354.51685, 0.45895, 0.11362, 0.11874)
## jodaSigma = c(2.3029, 17.903, 1019.9, 0.40010, 0.13889, 0.19864)

## makeHeatmap("regression-poi.pdf", poiTheta, poiSigma)
## makeHeatmap("regression-hsqldb.pdf", hsqldbTheta, hsqldbSigma)
## makeHeatmap("regression-jfree.pdf", jfreeTheta, jfreeSigma)
## makeHeatmap("regression-joda.pdf", jodaTheta, jodaSigma)
