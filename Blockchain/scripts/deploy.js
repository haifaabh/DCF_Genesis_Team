const hre = require("hardhat");

async function main() {
  await hre.run('compile');

  const Contract = await hre.ethers.getContractFactory("FoodTracking");

  const contract = await Contract.deploy();

  await contract.waitForDeployment();

  console.log(`Contract deployed at: ${contract.address}`);
}

// Handle errors properly
main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
