import {
  View,
  Text,
  ScrollView,
  TouchableOpacity,
  SafeAreaView,
  Alert,
} from "react-native";
import { useLocalSearchParams, useRouter } from "expo-router";
import { useEffect, useState } from "react";
import { format, addDays } from "date-fns";
import CustomHeader from "@/components/CustomHeader";
import axios from "axios";
import { useGlobalContext } from "@/context/GlobalProvider";
import FormField from "@/components/FormField";

// Generate next 7 days
const generateDates = () => {
  const dates = [];
  for (let i = 0; i < 7; i++) {
    dates.push(addDays(new Date(), i));
  }
  return dates;
};

export default function BookingScreen() {
  const { user } = useGlobalContext();
  const { id: dID, appointmentId } = useLocalSearchParams();
  const router = useRouter();
  const [slots, setSlots] = useState<string[]>([]);
  const [selectedDate, setSelectedDate] = useState(new Date());
  const [selectedTime, setSelectedTime] = useState(null);
  const [complains, setComplains] = useState("");

  const dates = generateDates();

  useEffect(() => {
    getDoctorTimings();
    if (appointmentId) {
      getAppointment();
    }
  }, []);

  // Mock data for available time slots
  const generateTimeSlots = (timings) => {
    const slots = timings.split(",");
    return slots;
  };

  const getDoctorTimings = async () => {
    try {
      const response = await axios.get(
        `http://192.168.43.29:8080/doctors/timings/${dID}`
      );
      setSlots(generateTimeSlots(response.data));
    } catch (err) {
      Alert.alert(err.toString());
    }
  };

  const getAppointment = async () => {
    try {
      const response = await axios.get(
        `http://192.168.43.29:8080/appointments/${appointmentId}`
      );
      if (response.status == 200) {
        setSelectedDate(new Date(response.data.appointmentDate));
        setSelectedTime(response.data.appointmentTime);
        setComplains(response.data.complains);
      }
    } catch (err) {
      Alert.alert(err.toString());
    }
  };

  const handleBooking = async () => {
    try {
      const response = await axios.post(
        "http://192.168.43.29:8080/appointments",
        {
          appointmentDate: format(selectedDate, "yyyy:MM:dd"),
          appointmentTime: selectedTime,
          patientId: user.id,
          doctorId: dID,
          complains: complains,
          status: "PENDING",
        }
      );
      if (response.status == 200) {
        router.replace("/(tabs)/appointments");
      } else {
        Alert.alert("Error", "Something Wrong in Input!");
      }
    } catch (err) {
      Alert.alert("Error", err.toString());
    }
  };

  const handleUpdate = async () => {
    try {
      const response = await axios.put(
        "http://192.168.43.29:8080/appointments",
        {
          appointmentId:appointmentId,
          appointmentDate: format(selectedDate, "yyyy:MM:dd"),
          appointmentTime: selectedTime,
          patientId: user.id,
          doctorId: dID,
          complains: complains,
          status: "PENDING",
        }
      );
      if (response.status == 200) {
        router.replace("/(tabs)/appointments");
      } else {
        Alert.alert("Error", "Something Wrong in Input!");
      }
    } catch (err) {
      Alert.alert("Error", err.toString());
    }
  };

  return (
    <SafeAreaView className="flex-1 bg-primaryBlack">
      <Text className="text-primaryLight mt-10 p-4 text-2xl font-pbold">
        Choose Date and time
      </Text>
      <ScrollView>
        <View className=" p-4 mb-4 ">
          <Text className="text-lg font-psemibold text-primaryLight mb-4">
            Select Date
          </Text>
          <ScrollView
            horizontal
            showsHorizontalScrollIndicator={false}
            className="flex-row"
          >
            {dates.map((date) => (
              <TouchableOpacity
                key={date.toISOString()}
                className={`w-16 h-20 rounded-xl items-center justify-center mr-3 ${
                  date.toDateString() === selectedDate.toDateString()
                    ? "bg-blue-600"
                    : "bg-slate-100"
                }`}
                onPress={() => setSelectedDate(date)}
              >
                <Text
                  className={`text-sm font-pregular ${
                    date.toDateString() === selectedDate.toDateString()
                      ? "text-primaryLight"
                      : "text-slate-500"
                  }`}
                >
                  {format(date, "EEE")}
                </Text>
                <Text
                  className={`text-xl font-pregular ${
                    date.toDateString() === selectedDate.toDateString()
                      ? "text-primaryLight"
                      : "text-slate-500"
                  }`}
                >
                  {format(date, "d")}
                </Text>
              </TouchableOpacity>
            ))}
          </ScrollView>
        </View>

        <View className=" p-4 mb-4">
          <Text className="text-lg font-psemibold text-primaryLight mb-4">
            Available Time Slots
          </Text>
          <View className="flex-row flex-wrap gap-3">
            {slots.map((time) => (
              <TouchableOpacity
                key={time}
                className={`py-3 px-4 rounded-lg ${
                  selectedTime === time ? "bg-blue-600" : "bg-secondaryBlack"
                }`}
                onPress={() => setSelectedTime(time)}
              >
                <Text
                  className={
                    /* selectedTime === time ? */ "text-white font-pregular" /*  : "text-slate-800" */
                  }
                >
                  {time}
                </Text>
              </TouchableOpacity>
            ))}
          </View>
        </View>

        <View className="mx-[5%]">
          <FormField
            title={"Complains"}
            value={complains}
            placeholder={"Complains as Comma separated values"}
            handleChangeText={(e) => setComplains(e)}
            otherStyles={"mb-7"}
          />
        </View>

        <TouchableOpacity
          className={`mx-4 p-4 rounded-xl items-center ${
            selectedTime ? "bg-blue-600" : "bg-slate-500"
          }`}
          onPress={appointmentId ? handleUpdate : handleBooking}
          disabled={!selectedTime}
        >
          <Text className="text-white text-base font-semibold">
            {appointmentId ? "Update Appointment" : " Book Appointment"}
          </Text>
        </TouchableOpacity>
      </ScrollView>
    </SafeAreaView>
  );
}
