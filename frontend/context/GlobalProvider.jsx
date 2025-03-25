import React, { createContext, useContext, useEffect, useState } from "react";
import * as SecureStore from "expo-secure-store";
import axios from "axios";
import { Alert } from "react-native";

const GlobalContext = createContext();
export const useGlobalContext = () => useContext(GlobalContext);

const GlobalProvider = ({ children }) => {
  const [isLogged, setIsLogged] = useState(false);
  const [user, setUser] = useState(null);

  const getToken = async () => {
    return {
      mobNo: await SecureStore.getItemAsync("mobNo"),
      password: await SecureStore.getItemAsync("password"),
    };
  };

  const setToken = async (mobNo, password) => {
    await SecureStore.setItemAsync("mobNo", mobNo);
    await SecureStore.setItemAsync("password", password);
    setIsLogged(true);
  };

  const deleteToken = async () => {
    await SecureStore.deleteItemAsync("mobNo");
    await SecureStore.deleteItemAsync("password");
    setIsLogged(false)
  };

  useEffect(() => {
    const fetchToken = async () => {
      const { mobNo, password } = await getToken();
      if (mobNo != null && password != null) {
        try {
          const response = await axios.post(
            "http://192.168.43.29:8080/patients/logIn",
            {
              mobNo: mobNo,
              password: password,
            }
          );

          if (response.status == 200) {
            setIsLogged(true);
            setUser(response.data);
          }
        } catch (err) {
          Alert.alert("Error", err.ToString());
        }
      }
    };

    fetchToken();
  }, []);

  return (
    <GlobalContext.Provider
      value={{
        isLogged,
        setIsLogged,
        user,
        setUser,
        setToken,
        deleteToken,
      }}
    >
      {children}
    </GlobalContext.Provider>
  );
};

export default GlobalProvider;
