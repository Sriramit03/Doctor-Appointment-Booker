import { useState } from "react";
import { Link, router } from "expo-router";
import { SafeAreaView } from "react-native-safe-area-context";
import { View, Text, ScrollView, Dimensions, Alert, Image } from "react-native";
import FormField from "@/components/FormField";
import CustomButton from "@/components/CustomButton";
import axios from "axios";
import { useGlobalContext } from "@/context/GlobalProvider";

const SignUp = () => {
  const { setUser, setToken } = useGlobalContext();
  const [isSubmitting, setSubmitting] = useState(false);
  const [form, setForm] = useState({
    name: "",
    age: 0,
    mobileNo: "",
    password: "",
    place: "",
  });

  const submit = async () => {
    if (
      form.name === "" ||
      form.mobileNo === "" ||
      form.password === "" ||
      form.place === "" ||
      form.age === 0
    ) {
      Alert.alert("Error", "Please fill in all fields");
    }
    setSubmitting(true);
    try {
      const response = await axios.post(
        "http://192.168.43.29:8080/patients",
        form
      );
      console.log("response data", response.data, ":");
      if (response.data != "") {
        await setUser(response.data);
        await setToken(form.mobileNo, form.password);
        router.replace("/(tabs)");
      } else {
        Alert.alert(
          "User ALready Registered",
          "Please Log In to continue ...."
        );
      }
    } catch (error) {
      console.error("Error fetching data:", error);
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <SafeAreaView className="bg-primaryBlack h-full">
      <ScrollView>
        <View
          className="w-full px-4 my-6"
          style={{
            minHeight: Dimensions.get("window").height - 100,
          }}
        >
          {/* <Image
            source={images.logo}
            resizeMode="contain"
            className="w-[115px] h-[34px]"
          /> */}

          <Text className="text-2xl font-semibold text-primaryLight mt-10 font-psemibold">
            Sign Up to DocBridge
          </Text>

          <FormField
            title="Username"
            value={form.name}
            handleChangeText={(e) => setForm({ ...form, name: e })}
            otherStyles="mt-10"
            placeholder={undefined}
          />

          <FormField
            title="Mobile Number"
            value={form.mobileNo}
            handleChangeText={(e) => setForm({ ...form, mobileNo: e })}
            otherStyles="mt-7"
            keyboardType="phone-pad"
            placeholder={undefined}
          />
          <FormField
            title="Age"
            value={form.age}
            handleChangeText={(e) => setForm({ ...form, age: e })}
            otherStyles="mt-7"
            keyboardType="numeric"
            placeholder={undefined}
          />
          <FormField
            title="Place"
            value={form.place}
            handleChangeText={(e) => setForm({ ...form, place: e })}
            otherStyles="mt-7"
            placeholder={undefined}
          />

          <FormField
            title="Password"
            value={form.password}
            handleChangeText={(e) => setForm({ ...form, password: e })}
            otherStyles="mt-7"
            placeholder={undefined}
          />

          <CustomButton
            title="Sign Up"
            handlePress={submit}
            containerStyles="mt-7"
            isLoading={isSubmitting}
            textStyles={undefined}
          />

          <View className="flex justify-center pt-5 flex-row gap-2">
            <Text className="text-lg text-gray-100 font-pregular">
              Have an account already?
            </Text>
            <Link
              href="/(auth)/sign-in"
              className="text-lg font-psemibold text-primaryBlue"
            >
              Login
            </Link>
          </View>
        </View>
      </ScrollView>
    </SafeAreaView>
  );
};

export default SignUp;
