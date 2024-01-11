import React, { useEffect, useState } from "react";
import { getAllBoosterPack } from "../Utils/ApiFunctions";

const Option = () => {
  const [boosterPacks, setBoosterPacks] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const packs = await getAllBoosterPack();
        setBoosterPacks(packs);
      } catch (error) {
        console.error("Error getting data", error);
      }
    };

    fetchData();
  }, []);

  return (
    <>
      {boosterPacks.map((pack, index) => (
        <option key={index} value={pack.id}>
          {pack.name}
        </option>
      ))}
    </>
  );
};

export default Option;
