// SPDX-License-Identifier: MIT
pragma solidity ^0.8.19;

contract FoodTracking {
    struct Donation {
        string foodType;
        string quantity;
        address donor;
        bool fulfilled;
    }

    Donation[] public donations;

    function pledgeFood(string calldata _foodType, string calldata _quantity) public {
        donations.push(Donation(_foodType, _quantity, msg.sender, false));
    }

    function getDonations() public view returns (Donation[] memory) {
        return donations;
    }

    function FulfillFood(uint _id) public {
        require(!donations[_id].fulfilled, "Already verified");
        donations[_id].fulfilled = true;
    }
}