-- Create the Counties table
CREATE TABLE Counties (
    CountyID INT PRIMARY KEY,
    CountyName VARCHAR(255)
);

-- Insert unique county names into the Counties table
INSERT INTO Counties (CountyID, CountyName)
VALUES
    (1, 'Kericho'),
    (2, 'Another County'),
    -- Add more counties as needed

-- Create the Regions table
CREATE TABLE Regions (
    RegionID INT PRIMARY KEY,
    RegionName VARCHAR(255)
);

-- Insert unique region names into the Regions table
INSERT INTO Regions (RegionID, RegionName)
VALUES
    (1, 'West'),
    (2, 'Another Region'),
    -- Add more regions as needed

-- Create the SubCounties table
CREATE TABLE SubCounties (
    SubCountyID INT PRIMARY KEY,
    SubCountyName VARCHAR(255),
    CountyID INT,
    RegionID INT,
    FOREIGN KEY (CountyID) REFERENCES Counties(CountyID),
    FOREIGN KEY (RegionID) REFERENCES Regions(RegionID)
);

-- Insert unique sub-county names into the SubCounties table
INSERT INTO SubCounties (SubCountyID, SubCountyName, CountyID, RegionID)
VALUES
    (1, 'ML25', 1, 1),
    (2, '101', 1, 1),
    -- Add more sub-county names as needed

-- Create the Wards table
CREATE TABLE Wards (
    WardID INT PRIMARY KEY,
    WardName VARCHAR(255),
    SubCountyID INT,
    FOREIGN KEY (SubCountyID) REFERENCES SubCounties(SubCountyID)
);

-- Insert unique ward names into the Wards table
INSERT INTO Wards (WardID, WardName, SubCountyID)
VALUES
    (1, 'WALDAI', 1),
    (2, 'KIPKOYAN', 1),
    -- Add more ward names as needed

-- Create the TeaVarieties table
CREATE TABLE TeaVarieties (
    VarietyID INT PRIMARY KEY,
    VarietyName VARCHAR(255)
);

-- Insert unique tea variety names into the TeaVarieties table
INSERT INTO TeaVarieties (VarietyID, VarietyName)
VALUES
    (1, 'Chinary'),
    (2, 'Assam'),
    (3, 'Cambod'),
    -- Add more tea varieties as needed

-- Create the TeaCultivars table
CREATE TABLE TeaCultivars (
    CultivarID INT PRIMARY KEY,
    CultivarName VARCHAR(255)
);

-- Insert unique tea cultivar names into the TeaCultivars table
INSERT INTO TeaCultivars (CultivarID, CultivarName)
VALUES
    (1, 'TRFK306'),
    (2, 'CL51'),
    (3, 'BB35'),
    (4, 'CL7'),
    (5, '1510'),
    (6, '1619'),
    (7, '31 / ''8'),
    -- Add more tea cultivars as needed

-- Create the FarmingTypes table
CREATE TABLE FarmingTypes (
    FarmingTypeID INT PRIMARY KEY,
    FarmingTypeName VARCHAR(255)
);

-- Insert unique farming type names into the FarmingTypes table
INSERT INTO FarmingTypes (FarmingTypeID, FarmingTypeName)
VALUES
    (1, 'Small Scale'),
    (2, 'Large Scale'),
    -- Add more farming types as needed

-- Create the PaymentMethods table
CREATE TABLE PaymentMethods (
    PaymentMethodID INT PRIMARY KEY,
    PaymentMethodName VARCHAR(255)
);

-- Insert unique payment method names into the PaymentMethods table
INSERT INTO PaymentMethods (PaymentMethodID, PaymentMethodName)
VALUES
    (1, 'Mobile Money'),
    (2, 'Bank Transfer'),
    -- Add more payment methods as needed

-- Create the tea_farmers table with foreign key relationships
CREATE TABLE tea_farmers (
    GrowerNumber VARCHAR(255) PRIMARY KEY,
    GrowerName VARCHAR(255),
    BuyingCentre VARCHAR(255),
    WardID INT,
    SubCountyID INT,
    CountyID INT,
    RegionID INT,
    TeaVarietyID INT,
    TeaCultivarID INT,
    TotalTeaBushes INT,
    AgeOfTeaBushYears INT,
    ProductivityPerBushYear DECIMAL(10, 2),
    FarmingTypeID INT,
    MembershipInTeaAssociation VARCHAR(255),
    TotalFertilizerPerYearAcre INT,
    AverageAnnualTeaProduction DECIMAL(10, 2),
    PaymentMethodID INT,
    DateGreenleafAgreementSigned DATE,
    FOREIGN KEY (WardID) REFERENCES Wards(WardID),
    FOREIGN KEY (SubCountyID) REFERENCES SubCounties(SubCountyID),
    FOREIGN KEY (CountyID) REFERENCES Counties(CountyID),
    FOREIGN KEY (RegionID) REFERENCES Regions(RegionID),
    FOREIGN KEY (TeaVarietyID) REFERENCES TeaVarieties(VarietyID),
    FOREIGN KEY (TeaCultivarID) REFERENCES TeaCultivars(CultivarID),
    FOREIGN KEY (FarmingTypeID) REFERENCES FarmingTypes(FarmingTypeID),
    FOREIGN KEY (PaymentMethodID) REFERENCES PaymentMethods(PaymentMethodID)
);
