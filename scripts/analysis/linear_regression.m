warning("off");

%% =========== Part 0: Define Functions =============

% Normalizes the data
function [X_norm, sigma] = featureNormalize(X)
    sigma = std(X);
    X_norm = bsxfun(@rdivide, X, sigma);
endfunction

% % Computes the cost and gradient functions for regularized linear
% % regression.  Assumes a 0 intercept (i.e., does NOT ignore
% % theta(1) when computing gradient)
% function [J, grad] = linearRegCostFunction(X, y, theta, lambda)
%     m = length(y);   
%     J = sum(((X * theta) - y) .^ 2) / (2*m) + (lambda / (2*m)) * sum(theta .^ 2);
%     grad = (X' * (X*theta - y) ) / m + (lambda / m) .* theta;
% endfunction
    
% Computes the cost and gradient functions for regularized linear
% regression.  Does not include regularization term when computing
% the gradient for theta(1) (i.e. assumes non-zero intercept)
function [J, grad] = linearRegCostFunction(X, y, theta, lambda)
    m = length(y);   
    J = sum(((X * theta) - y) .^ 2) / (2*m) + (lambda / (2*m)) * sum(theta .^ 2);
    grad = (X' * (X*theta - y) ) / m;
    temp = grad(1);
    grad = grad + (lambda / m) .* theta;
    grad(1) = temp;
endfunction

% Builds a model given the input data and lambda
function [theta] = trainLinearReg(X, y, lambda)
    initial_theta = zeros(size(X, 2), 1); 
    costFunction = @(t) linearRegCostFunction(X, y, t, lambda);
    options = optimset('MaxIter', 500, 'GradObj', 'on');
    theta = fmincg(costFunction, initial_theta, options);
endfunction

% Determines the error on the training and validation sets for a range of lambda values
function [lambda_vec, error_train, error_val] = validationCurve(X, y, Xval, yval)
    lambda_vec = [0 0.001 0.003 0.01 0.03 0.1 0.3 1 3 10 30 100]';
    error_train = zeros(length(lambda_vec), 1);
    error_val = zeros(length(lambda_vec), 1);
    for i = 1:length(lambda_vec)
        lambda = lambda_vec(i);
        % Train with the input lambda, but compute errors with
        % lambda = 0
        theta = trainLinearReg(X, y, lambda);
        error_train(i) = linearRegCostFunction(X, y, theta, 0);
        error_val(i) = linearRegCostFunction(Xval, yval, theta, 0);  
    end
endfunction

% Generates the training and cross validation set errors needed to
% plot a learning curve
function [error_train, error_val] = learningCurve(X, y, Xval, yval, lambda)
    m = size(X, 1);
    error_train = zeros(m, 1);
    error_val = zeros(m, 1);
    for i = 1:m
        % Train with the input lambda, but compute errors with
        % lambda = 0
        theta = trainLinearReg(X(1:i, :), y(1:i), lambda);
        error_train(i) = linearRegCostFunction(X(1:i, :), y(1:i), theta, 0);
        error_val(i) = linearRegCostFunction(Xval, yval, theta, 0);
        if (mod(m, 100) == 0)
            fprintf('Working...')
        endif
    end
endfunction

%% =========== Part 1: Load and Visualize Data =============

clear;     % Delete vars from the symbol table
close all; % Close figure windows
clc;       % Clear the terminal
fprintf('Loading data ...\n')

% Variable defs
numInputFeatures = 4;

% Load from file (rows have been randomly permuted already)
% Columns are size, block.coverage, kill.score
results = csvread("/home/laura/workspace/unified-test-analysis-toolkit/test-subjects/closure-results/randomSuiteResultsPermuted.csv");
% results = csvread(argv(){1});

% Make an expanded results matrix
% Inputs are normalized before adding effectiveness
suiteSize = results(:,1);
% coverage = results(:,2);
% coverage = coverage .+ ((coverage == 0) .* 0.0000001); % Prevent NaN
effectiveness = results(:,3);
results = [log(suiteSize), sqrt(suiteSize), suiteSize];
% log(coverage), sqrt(coverage), coverage];
[results, sigma] = featureNormalize(results);
results = [ones(size(results, 1), 1) results effectiveness];
% results = [results effectiveness];

% Compute bounds of training and cross validation sets
numDataPoints = size(results, 1);
lastTrainingRow = floor(0.6 * numDataPoints);
lastCVRow = floor(0.8 * numDataPoints);

% Set X, Xval, Xtest, and y, yval and ytest
X = results(1:lastTrainingRow, 1:numInputFeatures);
Xval = results((lastTrainingRow+1):lastCVRow, 1:numInputFeatures);
Xtest = results((lastCVRow+1):numDataPoints, 1:numInputFeatures);
y = results(1:lastTrainingRow, numInputFeatures + 1);
yval = results((lastTrainingRow+1):lastCVRow, numInputFeatures + 1);
ytest = results((lastCVRow+1):numDataPoints, numInputFeatures + 1);

% Sanity check
X(1:10,:)
y(1:10,:)
sigma

% m = Number of training examples
m = size(X, 1);

% Plot normalized training data
% scatter3(X(:,2), X(:,5), y);
% xlabel('Size of suite');
% ylabel('Block coverage');
% zlabel('Effectiveness');

% fprintf('Program paused. Press enter to continue.\n');
% pause;

%% =========== Part 2: Select Optimal Lambda and Model =============

% There are 2^numInputFeatures - 1 possible models
% Find the best lambda for each model first
bestLambdas = zeros(2^numInputFeatures - 1, 1);
for i = 1:(2^numInputFeatures - 1)
    mask = dec2binvec(i, numInputFeatures);
    Xtemp = X .* repmat(mask, m, 1);
    Xvaltemp = Xval .* repmat(mask, size(Xval, 1), 1);
    [lambdaVec, errorTrain, errorVal] = validationCurve(Xtemp, y, ...
                                                      Xvaltemp, yval);
    
    % close all;
    % plot(lambdaVec, errorTrain, lambdaVec, errorVal);
    % legend('Train', 'Cross Validation');
    % xlabel('lambda');
    % ylabel('Error');
    
    fprintf('lambda\t\tTrain Error\tValidation Error\n');
    for j = 1:length(lambdaVec)
    	fprintf(' %f\t%f\t%f\n', ...
               lambdaVec(j), errorTrain(j), errorVal(j));
    end
    lowestErrorIndex = max(find(errorVal == min(errorVal)));
    lowestError = errorVal(lowestErrorIndex);
    % [lowestError, lowestErrorIndex] = min(errorVal);
    bestLambdas(i) = lambdaVec(lowestErrorIndex);
    fprintf('Lowest cost is %f and best lambda is %f\n', lowestError, ...
            bestLambdas(i));
    fprintf('using mask:\n');
    mask
end

% Now train each model with that lambda and evaluate them on the
% test set
costs = zeros(2^numInputFeatures - 1, 1);
for i = 1:(2^numInputFeatures - 1)
    mask = dec2binvec(i, numInputFeatures);
    Xtemp = X .* repmat(mask, m, 1);
    [theta] = trainLinearReg(Xtemp, y, bestLambdas(i));
    Xtesttemp = Xtest .* repmat(mask, size(Xtest,1), 1);
    costs(i) = linearRegCostFunction(Xtesttemp, ytest, theta, bestLambdas(i));
end

% Pick the model with the lowest cost and save the best mask
[bestCost, bestMask] = min(costs);
fprintf('\nMinimum cost on test data is %f with the mask:\n', bestCost)
mask = dec2binvec(bestMask, numInputFeatures)

% Apply the best mask to the input matrices and save the best lambda
X = X .* repmat(mask, size(X,1), 1);
Xval = Xval .* repmat(mask, size(Xval,1), 1);
Xtest = Xtest .* repmat(mask, size(Xtest,1), 1);
lambda = bestLambdas(bestMask);

% fprintf('Program paused. Press enter to continue.\n');
% pause;

%% =========== Part 3: Train the Best Model =============

% Train with the model and lambda selected in the previous step
fprintf('Using lambda:\n')
lambda
[theta] = trainLinearReg(X, y, lambda);

% Plot the data points (the real observations)
% close all;
% hold on;
% scatter3(suiteSize, coverage, effectiveness, 8, [0 0 0]);

% Plot the predicted effectiveness as a 3D mesh
% [xs, ys] = meshgrid(linspace(1, 3000, 110)', linspace(0.001, 1, ...
%                                                   110)');
% zs = zeros(size(xs));
% for i = 1:size(xs, 1)
%     for j = 1:size(xs, 2)
%         ex = xs(i, j);
%         why = ys(i, j);
%         t = ([log(ex) sqrt(ex) ex log(why) sqrt(why) why] ./ sigma) .* mask;
%         zs(i, j) = t*theta;
%     end
% end
% mesh(xs, ys, zs);
% xlabel('Size of suite', 'FontSize', 16);
% ylabel('Block coverage', 'FontSize', 16);
% zlabel('Effectiveness', 'FontSize', 16);
% set(gca,'position',[.18,.18,.7,.7]);
% grid on;
% hold off;
    
% Print theta coefficients and cost for reference
fprintf('The coefficients are:\n')
theta

% fprintf('Program paused. Press enter to continue.\n');
% pause;

%% =========== Part 4: Plot Learning Curve =============

% % Find the error for different training set sizes
% [error_train, error_val] = learningCurve(X, y, Xval, yval, lambda);

% % Plot the learning curve
% close all;
% h = figure;
% set (h,'papertype', '<custom>')
% set (h,'paperunits','inches');
% set (h,'papersize',[3.2 3.2])
% set (h,'paperposition', [0,0,[3.2 3.2]])
% set (h,'defaultaxesposition', [0.18, 0.15, 0.75, 0.75])
% set (0,'defaultaxesfontsize', 14)

% plot(1:m, error_train, 1:m, error_val);
% %title('Learning curve for linear regression')
% legend('Training', 'Cross validation')
% xlabel('Number of training examples')
% ylabel('Error')
% axis([0 3000 0 0.01])
% print('rq3-hsqldb.eps','-deps', '-color')

% fprintf('Program paused. Press enter to continue.\n');
% pause;

